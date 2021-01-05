package brown.jeff.cocktailapp.features.searchdrink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.data.api.Result
import brown.jeff.cocktailapp.data.api.Result.Success
import brown.jeff.cocktailapp.data.repository.DrinkRepositoryImpl
import brown.jeff.cocktailapp.features.searchdrink.model.SearchDrinkListModel
import brown.jeff.cocktailapp.features.searchdrink.model.toSearchDrinkListModel
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchRecentDrinksViewModel(private val drinkRepositoryImpl: DrinkRepositoryImpl) : ViewModel() {
    private val recentDrinks = MutableLiveData<List<SearchDrinkListModel>>()
    val popularDrinks: LiveData<List<SearchDrinkListModel>>
        get() = recentDrinks

    private val _loadingDrinks = MutableLiveData<Boolean>()
    val loadingDrinks: LiveData<Boolean>
        get() = _loadingDrinks

    private val _displayError = MutableLiveData<String>()
    val displayError: LiveData<String>
        get() = _displayError

    private val _currentDrinks = MutableLiveData<List<SearchDrinkListModel>>()

    fun getRecentDrinks() {
        _loadingDrinks.value = true
        viewModelScope.launch {
            when (val result = drinkRepositoryImpl.getPopularDrinks()) {
                is Success -> {
                    recentDrinks.postValue(result.data.drinks.toSearchDrinkListModel())
                    _loadingDrinks.postValue(false)
                    _currentDrinks.value = result.data.drinks.toSearchDrinkListModel()
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
            when (val result = drinkRepositoryImpl.getDrinksByName(drinkName)) {
                is Success -> {
                    if (result.data.drinks.isNullOrEmpty()) {
                        _loadingDrinks.value = false
                        _displayError.value = "No drink found"
                    } else {
                        if (validateParameter(drinkName)) {
                            recentDrinks.value = result.data.drinks.toSearchDrinkListModel()
                            _loadingDrinks.value = false
                        }
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