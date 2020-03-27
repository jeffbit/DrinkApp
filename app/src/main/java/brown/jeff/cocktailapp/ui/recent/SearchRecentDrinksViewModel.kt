package brown.jeff.cocktailapp.ui.recent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.repositories.DrinkRepository
import brown.jeff.cocktailapp.network.Result.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import brown.jeff.cocktailapp.network.Result

class SearchRecentDrinksViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {
    private val recentDrinks = MutableLiveData<List<Drink>>()
    val popularDrinks: LiveData<List<Drink>>
        get() = recentDrinks

    private val _loadingDrinks = MutableLiveData<Boolean>()
    val loadingDrinks: LiveData<Boolean>
        get() = _loadingDrinks

    private val _displayError = MutableLiveData<String>()
    val displayError: LiveData<String>
        get() = _displayError


    fun getRecentDrinks() {
        _loadingDrinks.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                when (val result = drinkRepository.getPopularDrinks()) {
                    is Success -> {
                        Timber.i(result.data.drinks.toString())
                        recentDrinks.postValue(result.data.drinks)
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


}