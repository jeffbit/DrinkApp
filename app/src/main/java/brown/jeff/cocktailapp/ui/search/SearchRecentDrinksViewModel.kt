package brown.jeff.cocktailapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.model.Drinks
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.network.Result.Success
import brown.jeff.cocktailapp.repositories.DrinkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchRecentDrinksViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {
    private val recentDrinks = MutableLiveData<List<Drink>>()
    val popularDrinks: LiveData<List<Drink>>
        get() = recentDrinks

    //todo: progressbar for drink loading in search view
    private val _loadingDrinks = MutableLiveData<Boolean>()
    val loadingDrinks: LiveData<Boolean>
        get() = _loadingDrinks

    private val _displayError = MutableLiveData<String>()
    val displayError: LiveData<String>
        get() = _displayError

    private val _currentDrinks = MutableLiveData<List<Drink>>()

    fun getRecentDrinks() {
        _loadingDrinks.value = true
        viewModelScope.launch {
            when (val result = drinkRepository.getPopularDrinks()) {
                is Success -> {
                    recentDrinks.postValue(result.data.drinks)
                    _loadingDrinks.postValue(false)
                    _currentDrinks.value = result.data.drinks
                }
                is Result.Failure -> {
                    _displayError.postValue(result.errors.toString())
                    _loadingDrinks.postValue(false)
                }
            }
        }

    }

    fun searchDrinkByName(drinkName: String) {
        _loadingDrinks.value = true
        viewModelScope.launch {
            when (val result = drinkRepository.getDrinksByName(drinkName)) {
                is Success -> {
                    if (validateParameter(drinkName)) {
                        recentDrinks.value = result.data.drinks
                        _loadingDrinks.value = false
                    }
                    _loadingDrinks.value = false
                    Timber.e("Success")
                }
                is Result.Failure -> {
                    _displayError.value = result.errors.toString()
                    _loadingDrinks.value = false
                    Timber.e("Failure : ${result.errors}")
                }
            }
        }
    }


    //todo: validate search results. "ERROR HANDLING NEEDS TO TAKE PLACE HERE"
    private fun validateResults(drinks: Result<Drinks>): Boolean {
        return if (_currentDrinks.value == drinks) {
            _displayError.value = "No drink"
            false
        } else {

            true
        }

    }

    //error appears for numbers. need to setup error to appear if nothing is returned
    private fun validateParameter(drinkName: String): Boolean {
        if (drinkName.matches(".*\\d.*".toRegex())) {
            _displayError.value = "Search cannot contain numbers"
            Timber.e("number error")
            return false
        } else if (drinkName.isEmpty()) {
            _displayError.value = "Invalid Search"
            Timber.e("invalid search")
            return false
        }

        Timber.e("clean search")
        _displayError.value = null
        return true
    }
}