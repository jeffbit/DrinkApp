package brown.jeff.cocktailapp.ui.drinkclicked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.repositories.DrinkRepositoryImpl
import brown.jeff.cocktailapp.repositories.FavoriteDrinkRepositoryImpl
import kotlinx.coroutines.launch
import timber.log.Timber

class DrinkClickedViewModel(
    private val drinkRepositoryImpl: DrinkRepositoryImpl,
    private val favoriteDrinkRepositoryImpl: FavoriteDrinkRepositoryImpl
) : ViewModel() {
    private val _relatedDrinks = MutableLiveData<List<Drink>>()
    val relatedDrinks: LiveData<List<Drink>>
        get() = _relatedDrinks

    private val _clickedDrink = MutableLiveData<Drink>()
    val clickedDrink: LiveData<Drink>
        get() = _clickedDrink

//    private val _drinkUpdated = MutableLiveData<Boolean>()
//    val drinkUpdated: LiveData<Boolean>
//        get() = _drinkUpdated


    fun addDrinkToFavorites() {
        viewModelScope.launch {
            if (_clickedDrink.value != null) {
                _clickedDrink.value.let {
                    favoriteDrinkRepositoryImpl.insertDrinksLocalDB(it!!)
                }
            }
        }
    }

    fun removeDrinkFromFavorites(drinkId: String) {
        viewModelScope.launch {
            favoriteDrinkRepositoryImpl.removeDrinkById(drinkId)
        }
    }

    fun setDrinkNull() {
        _clickedDrink.value = null
    }


    fun getDrinkById(drinkId: String) {
        viewModelScope.launch {
            when (val result = drinkRepositoryImpl.getDrinkById(drinkId)) {
                is Result.Success -> {
                    _clickedDrink.value = result.data.drinks[0]
                }
                is Result.Failure -> {
                    Timber.e(result.errors.toString())
                }
            }
        }
    }

    fun getDrinksByIngredient(ingredients: String? = "Vodka") {
        viewModelScope.launch {
            when (val result = drinkRepositoryImpl.getDrinksByIngredients(ingredients)) {
                is Result.Success -> {
                    Timber.e("Realted Drinks added")
                    _relatedDrinks.postValue(result.data.drinks)
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
