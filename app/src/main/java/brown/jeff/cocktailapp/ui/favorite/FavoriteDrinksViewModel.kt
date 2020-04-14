package brown.jeff.cocktailapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.repositories.DrinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteDrinksViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {

    private val _favoriteDrinks = MutableLiveData<List<Drink>>()
    val favoriteDrinks: LiveData<List<Drink>>
        get() = _favoriteDrinks

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    fun getDrinksFromLocalStorage() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = drinkRepository.getDrinksLocalDB()
                try {
                    result.value?.let { drinkList ->
                        if (!drinkList.isNullOrEmpty()) {
                            _favoriteDrinks.postValue(drinkList)
                        }
                    }

                } catch (e: Exception) {
                    _errorMessage.postValue(e.message)

                }
            }
        }
    }
}