package brown.jeff.cocktailapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.data.entities.DrinkEntity
import brown.jeff.cocktailapp.util.loadImage

class DrinkAdapter(
    private var drinkEntities: List<DrinkEntity>,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {


    fun updateDrinkList(drinkEntityList: List<DrinkEntity>) {
        val diffCallBack = DrinkDiffCallBack(drinkEntities, drinkEntityList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.drinkEntities = emptyList()
        this.drinkEntities = drinkEntityList
        diffResult.dispatchUpdatesTo(this)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.drink_adapter_view, parent, false)
        return DrinkViewHolder(view)


    }

    override fun getItemCount(): Int {
        return drinkEntities.size
    }


    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val currentDrink = drinkEntities[position]
        holder.bind(currentDrink, clickListener)


    }

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var drinkImageView: ImageView = itemView.findViewById(R.id.drink_imageview)
        private var drinkNameTextView: TextView = itemView.findViewById(R.id.drink_name)
        private var drinkContainer: CardView = itemView.findViewById(R.id.drink_cardview)


        fun bind(
            drinkEntity: DrinkEntity,
            clickListener: (String) -> Unit
        ) {
            drinkNameTextView.text = drinkEntity.drink
            //Glide loads image into view
            loadImage(drinkImageView, drinkEntity)
            drinkContainer.setOnClickListener {
                clickListener(drinkEntity.idDrink)
                drinkSelectedListener?.onDrinkSelected(drinkEntity, drinkImageView)
            }

        }

    }

    interface DrinkSelectedListener {
        fun onDrinkSelected(drinkEntity: DrinkEntity, imageView: ImageView)

    }

    var drinkSelectedListener: DrinkSelectedListener? = null


}





