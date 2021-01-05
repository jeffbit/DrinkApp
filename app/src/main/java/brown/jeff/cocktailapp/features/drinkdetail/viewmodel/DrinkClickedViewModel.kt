package brown.jeff.cocktailapp.features.drinkdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.data.api.Result
import brown.jeff.cocktailapp.data.repository.DrinkRepositoryImpl
import brown.jeff.cocktailapp.features.drinkdetail.model.DrinkDetailModel
import brown.jeff.cocktailapp.features.drinkdetail.model.toDrinkDetailModel
import brown.jeff.cocktailapp.features.drinklist.model.DrinkListModel
import brown.jeff.cocktailapp.features.drinklist.model.toDrinkListModel
import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.repository.FavoriteDrinkRepositoryImpl
import kotlinx.coroutines.launch
import timber.log.Timber

class DrinkClickedViewModel(
    private val drinkRepositoryImpl: DrinkRepositoryImpl,
    private val favoriteDrinkRepositoryImpl: FavoriteDrinkRepositoryImpl
) : ViewModel() {
    private val _relatedDrinks = MutableLiveData<List<DrinkListModel>>()
    val relatedDrinks: LiveData<List<DrinkListModel>>
        get() = _relatedDrinks

    private val _clickedDrink = MutableLiveData<DrinkDetailModel>()
    val clickedDrink: LiveData<DrinkDetailModel>
        get() = _clickedDrink

//    private val _drinkUpdated = MutableLiveData<Boolean>()
//    val drinkUpdated: LiveData<Boolean>
//        get() = _drinkUpdated


//    fun addDrinkToFavorites() {
//        viewModelScope.launch {
//            if (_clickedDrink.value != null) {
//                _clickedDrink.value.let {
//                    favoriteDrinkRepositoryImpl.insertDrinksLocalDB(it!!)
//                }
//            }
//        }
//    }

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
                    _clickedDrink.value = result.data.drinks[0].toDrinkDetailModel()
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
                    _relatedDrinks.postValue(result.data.drinks.toDrinkListModel())
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
