package brown.jeff.cocktailapp.features.favoritedrink.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.features.drinklist.model.DrinkListModel
import brown.jeff.cocktailapp.features.favoritedrink.model.DrinkFavoriteListModel
import brown.jeff.cocktailapp.util.loadImage

class FavoriteDrinkListAdapter(
    private var drinks: List<DrinkFavoriteListModel>,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<FavoriteDrinkListAdapter.DrinkViewHolder>() {


    fun updateDrinkList(drinkList: List<DrinkFavoriteListModel>) {
        val diffCallBack = DrinkDiffCallBack(drinks, drinkList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.drinks = emptyList()
        this.drinks = drinkList
        diffResult.dispatchUpdatesTo(this)

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

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var drinkImageView: ImageView = itemView.findViewById(R.id.drink_imageview)
        private var drinkNameTextView: TextView = itemView.findViewById(R.id.drink_name)
        private var drinkContainer: CardView = itemView.findViewById(R.id.drink_cardview)


        fun bind(
            drink: DrinkFavoriteListModel,
            clickListener: (String) -> Unit
        ) {
            drinkNameTextView.text = drink.title
            //Glide loads image into view
            loadImage(drinkImageView, drink)
            drinkContainer.setOnClickListener {
                clickListener(drink.id)
                drinkSelectedListener?.onDrinkSelected(drink, drinkImageView)
            }

        }

    }

    interface DrinkSelectedListener {
        fun onDrinkSelected(drink: DrinkFavoriteListModel, imageView: ImageView)

    }

    var drinkSelectedListener: DrinkSelectedListener? = null

    class DrinkDiffCallBack(
        private var oldDrinks: List<DrinkFavoriteListModel>,
        private var newDrinks: List<DrinkFavoriteListModel>
    ) :
        DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldDrinks[oldItemPosition].id == newDrinks[newItemPosition].id
        }


        override fun getOldListSize(): Int {
            return oldDrinks.size
        }

        override fun getNewListSize(): Int {
            return newDrinks.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldDrinks[oldItemPosition] == newDrinks[newItemPosition]
        }

    }


}





