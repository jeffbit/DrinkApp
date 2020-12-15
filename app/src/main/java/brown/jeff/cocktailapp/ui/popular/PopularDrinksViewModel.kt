package brown.jeff.cocktailapp.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.repositories.DrinkRepositoryImpl
import kotlinx.coroutines.launch

class PopularDrinksViewModel(private val drinkRepositoryImpl: DrinkRepositoryImpl) : ViewModel() {

    private val _popularDrinks = MutableLiveData<List<Drink>>()
    val popularDrinks: LiveData<List<Drink>>
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
                    _popularDrinks.postValue(result.data.drinks)
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