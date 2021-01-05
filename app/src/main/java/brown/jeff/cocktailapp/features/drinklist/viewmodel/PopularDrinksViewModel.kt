package brown.jeff.cocktailapp.features.drinklist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.data.api.Result
import brown.jeff.cocktailapp.data.repository.DrinkRepositoryImpl
import brown.jeff.cocktailapp.features.drinklist.model.DrinkListModel
import brown.jeff.cocktailapp.features.drinklist.model.toDrinkListModel
import kotlinx.coroutines.launch

class PopularDrinksViewModel(private val drinkRepositoryImpl: DrinkRepositoryImpl) : ViewModel() {

    private val _popularDrinks = MutableLiveData<List<DrinkListModel>>()
    val popularDrinks: LiveData<List<DrinkListModel>>
        get() = _popularDrinks

    private val _loadingDrinks = MutableLiveData<Boolean>()
    val loadingDrinks: LiveData<Boolean>
        get() = _loadingDrinks

    private val _displayError = MutableLiveData<String>()
    val displayError: LiveData<String>
        get() = _displayError


    fun getAllPopularDrinks() {
        _loadingDrinks.value = true
        _displayError.value = null
        viewModelScope.launch {
            when (val result = drinkRepositoryImpl.getPopularDrinks()) {
                is Result.Success -> {
                    _popularDrinks.postValue(result.data.drinks.toDrinkListModel())
                    _loadingDrinks.postValue(false)
                }
                is Result.Failure -> {
                    _displayError.postValue(result.errors.toString())
                    _loadingDrinks.postValue(false)
                }
            }
        }


    }


}