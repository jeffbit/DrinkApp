package brown.jeff.cocktailapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.repositories.FavoriteDrinkRepositoryImpl
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoriteDrinksViewModel(private val favoriteDrinkRepositoryImpl: FavoriteDrinkRepositoryImpl) :
    ViewModel() {


    private val _favoriteDrinks = MutableLiveData<List<Drink>>()
    val favoriteDrinks: LiveData<List<Drink>>
        get() = _favoriteDrinks


    fun getFavoriteDrinks() {
        viewModelScope.launch {
            _favoriteDrinks.postValue(favoriteDrinkRepositoryImpl.getDrinksLocalDB())
        }
    }


    fun deleteAllDrinks() {
        viewModelScope.launch {
            Timber.e("FavoriteViewmodel: Drinks deleted")
            favoriteDrinkRepositoryImpl.deleteAllDrinks()
            _favoriteDrinks.value = null

        }
    }

}