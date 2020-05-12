package brown.jeff.cocktailapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.repositories.DrinkRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoriteDrinksViewModel(private val drinkRepository: DrinkRepository) : ViewModel() {


    val getDrinks = drinkRepository.getDrinksLocalDB()


    fun deleteAllDrinks() {
        viewModelScope.launch {
            Timber.e("FavoriteViewmodel: Drinks deleted")
            drinkRepository.deleteAllDrinks()

        }
    }

}