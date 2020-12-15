package brown.jeff.cocktailapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import brown.jeff.cocktailapp.data.entities.DrinkEntity

class DrinkDiffCallBack(private var oldDrinkEntities: List<DrinkEntity>, private var newDrinkEntities: List<DrinkEntity>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDrinkEntities[oldItemPosition].idDrink == newDrinkEntities[newItemPosition].idDrink
    }


    override fun getOldListSize(): Int {
        return oldDrinkEntities.size
    }

    override fun getNewListSize(): Int {
        return newDrinkEntities.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDrinkEntities[oldItemPosition] == newDrinkEntities[newItemPosition]
    }

}
