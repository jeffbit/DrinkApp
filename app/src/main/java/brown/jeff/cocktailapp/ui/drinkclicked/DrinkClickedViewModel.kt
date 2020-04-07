package brown.jeff.cocktailapp.ui.drinkclicked

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import brown.jeff.cocktailapp.model.Drink

class DrinkClickedViewModel(private val sharedDrinkViewModelData: SharedDrinkViewModelData) : ViewModel() {
    // TODO: Implement the ViewModel

    val drink: LiveData<Drink>
        get() = sharedDrinkViewModelData.drink


    fun setDrinkValue(drink: Drink) {
        sharedDrinkViewModelData.setDrink(drink)
    }


}
