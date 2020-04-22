package brown.jeff.cocktailapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.repositories.DrinkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoriteDrinksViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {

    private val _favoriteDrinks = MutableLiveData<List<Drink>>()
     val favoriteDrinks: LiveData<List<Drink>>
        get() = _favoriteDrinks

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


val data = drinkRepository.data

    fun getDrinksFromLocalStorage() {
        Timber.e("Local DB Call")
        viewModelScope.launch {
            try {
                _favoriteDrinks.value = drinkRepository.getDrinksLocalDB().value
                Timber.e("Drinks retrieved")
            } catch (e: Exception) {
                Timber.e(e)
            }
        }

    }
}