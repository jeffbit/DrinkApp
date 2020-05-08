package brown.jeff.cocktailapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.repositories.DrinkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoriteDrinksViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage


    val getDrinks = drinkRepository.getDrinksLocalDB()


    fun deleteAllDrinks() {
        viewModelScope.launch {
            Timber.e("FavoriteViewmOdel: Drinks deleted")
            drinkRepository.deleteAllDrinks()
        }
    }
}