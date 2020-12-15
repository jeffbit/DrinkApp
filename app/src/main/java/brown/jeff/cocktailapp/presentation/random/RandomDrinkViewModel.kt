package brown.jeff.cocktailapp.ui.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.data.entities.DrinkEntity
import brown.jeff.cocktailapp.domain.usecase.Result
import brown.jeff.cocktailapp.data.repository.DrinkRepositoryImpl
import brown.jeff.cocktailapp.data.repository.FavoriteDrinkRepositoryImpl
import kotlinx.coroutines.launch
import timber.log.Timber

class RandomDrinkViewModel(
    private val drinkRepositoryImpl: DrinkRepositoryImpl,
    private val favoriteDrinkRepositoryImpl: FavoriteDrinkRepositoryImpl
) : ViewModel() {

    private val _drink = MutableLiveData<DrinkEntity>()
    val drinkEntity: LiveData<DrinkEntity>
        get() = _drink

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    fun getRandomDrink() {
        _loading.value = true
        viewModelScope.launch {
            when (val result = drinkRepositoryImpl.getRandomDrink()) {
                is Result.Success -> {
                    _loading.value = false
                    _drink.value = result.data.drinkEntities[0]
                    Timber.i(result.data.toString())
                }
                is Result.Failure -> {
                    _loading.value = false
                    Timber.e(result.errors.toString())
                    _errorMessage.value = result.errors.toString()
                }


            }
        }
    }

    fun addDrinkToFavorites(drinkEntity: DrinkEntity) {
        viewModelScope.launch {
            favoriteDrinkRepositoryImpl.insertDrinksLocalDB(drinkEntity)
        }
    }

    fun removeDrinkFromFavorites(drinkId: String) {
        viewModelScope.launch {
            favoriteDrinkRepositoryImpl.removeDrinkById(drinkId)
        }
    }


}
