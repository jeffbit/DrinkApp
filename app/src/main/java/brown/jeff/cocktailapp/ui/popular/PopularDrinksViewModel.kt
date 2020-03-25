package brown.jeff.cocktailapp.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.model.DrinkRepository
import brown.jeff.cocktailapp.network.Result
import kotlinx.coroutines.launch

class PopularDrinksViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {

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
        viewModelScope.launch {
            val result = drinkRepository.getPopularDrinks()
            when (result) {
                is Result.Success -> {
                    _popularDrinks.value = result.data.drinks
                    _loadingDrinks.value = false
                }
                is Result.Failure -> {
                    _displayError.value = result.errors.toString()
                    _loadingDrinks.value = false
                }
            }
        }

    }
}