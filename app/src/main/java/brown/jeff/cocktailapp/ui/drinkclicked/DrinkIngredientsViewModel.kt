package brown.jeff.cocktailapp.ui.drinkclicked

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import brown.jeff.cocktailapp.model.Drink

class DrinkIngredientsViewModel(private val sharedDrinkViewModelData: SharedDrinkViewModelData) : ViewModel() {

    val drink: LiveData<Drink>
        get() = sharedDrinkViewModelData.drink

}