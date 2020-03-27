package brown.jeff.cocktailapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.model.Drink
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DrinkAdapter(
    private var drinks: List<Drink>,
    private val clickListener: (Drink) -> Unit
) :
    RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {


    fun updateDrinkList(drinkList: List<Drink>) {
        this.drinks = drinkList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.drink_adapter_view, parent, false)
        return DrinkViewHolder(view)


    }

    override fun getItemCount(): Int {
        return drinks.size
    }


    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val currentDrink = drinks[position]
        holder.bind(currentDrink, clickListener)


    }

    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var drinkImageView: ImageView = itemView.findViewById(R.id.drink_imageview)
        private var drinkNameTextView: TextView = itemView.findViewById(R.id.drink_name)
        private var drinkContainer: CardView = itemView.findViewById(R.id.drink_cardview)

        fun bind(
            drink: Drink,
            clickListener: (Drink) -> Unit
        ) {
            drinkNameTextView.text = drink.drink
            //Glide loads image into view
            Glide.with(drinkImageView.context)
                .load(drink.drinkImg)
                .error(R.drawable.ic_broken_image_black_24dp)
                .apply(RequestOptions.centerCropTransform())
                .into(drinkImageView)

            drinkContainer.setOnClickListener { clickListener(drink) }

        }


    }


}


//todo: add onclick to allow user to click heart icon in corner of view to save and favorite cocktails
