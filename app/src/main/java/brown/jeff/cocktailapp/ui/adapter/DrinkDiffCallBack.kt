package brown.jeff.cocktailapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import brown.jeff.cocktailapp.model.Drink

class DrinkDiffCallBack(private var oldDrinks: List<Drink>, private var newDrinks: List<Drink>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDrinks[oldItemPosition].idDrink == newDrinks[newItemPosition].idDrink
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
