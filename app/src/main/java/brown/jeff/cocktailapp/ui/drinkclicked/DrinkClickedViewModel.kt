package brown.jeff.cocktailapp.ui.drinkclicked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.repositories.DrinkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class DrinkClickedViewModel(
    private val sharedDrinkViewModelData: SharedDrinkViewModelData,
    private val drinkRepository: DrinkRepository
) : ViewModel() {
    // TODO: Implement the ViewModel

    private val _relatedDrinks = MutableLiveData<List<Drink>>()
    val relatedDrinks: LiveData<List<Drink>>
        get() = _relatedDrinks

    val drink: LiveData<Drink>
        get() = sharedDrinkViewModelData.drink

    fun setDrinkValue(drink: Drink) {
        sharedDrinkViewModelData.setDrink(drink)
    }

    fun addDrinkToFavorites(drink: Drink) {
        viewModelScope.launch {
            drinkRepository.insertDrinksLocalDB(drink)
        }
    }

    fun removeDrinkFromFavorites(drinkId: String) {
        viewModelScope.launch {
            drinkRepository.removeDrinkById(drinkId)
        }
    }

    fun getDrinksByIngredient(ingredients: String? = "Vodka") {
        viewModelScope.launch {
            when (val result = drinkRepository.getDrinksByIngredients(ingredients)) {
                is Result.Success -> {
                    Timber.e("Realted Drinks added")
                    _relatedDrinks.value = result.data.drinks
//                    _loadingDrinks.value = false
                }
                is Result.Failure -> {
//                    _displayError.value = result.errors.toString()
                    Timber.e(result.errors.toString())
//                    _loadingDrinks.value = false
                }
            }
        }
    }


}
