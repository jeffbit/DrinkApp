package brown.jeff.cocktailapp.features.favoritedrink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.repository.FavoriteDrinkRepositoryImpl
import brown.jeff.cocktailapp.features.favoritedrink.model.DrinkFavoriteListModel
import brown.jeff.cocktailapp.features.favoritedrink.model.toDrinkFavoriteListModel
import kotlinx.coroutines.launch
import timber.log.Timber

class FavoriteDrinksViewModel(private val favoriteDrinkRepositoryImpl: FavoriteDrinkRepositoryImpl) :
    ViewModel() {


    private val _favoriteDrinks = MutableLiveData<List<DrinkFavoriteListModel>>()
    val favoriteDrinks: LiveData<List<DrinkFavoriteListModel>>
        get() = _favoriteDrinks


    fun getFavoriteDrinks() {
        viewModelScope.launch {
            _favoriteDrinks.postValue(favoriteDrinkRepositoryImpl.getDrinksLocalDB().toDrinkFavoriteListModel())
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