package brown.jeff.cocktailapp.data.repository

import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.room.DrinkDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FavoriteDrinkRepositoryImpl(
    private val drinkDao: DrinkDao) : FavoriteDrinkRepository {



    override suspend fun getDrinksLocalDB(): List<Drink> {
        Timber.e("Repository: Drinks Retrieved")
        return withContext(Dispatchers.IO) {
            drinkDao.getAllDrinks()
        }

    }

    override suspend fun deleteAllDrinks() {
        withContext(Dispatchers.IO) {
            Timber.e("Repository: Delete Drinks")
            drinkDao.deleteAllDrinks()
        }
    }

    override suspend fun insertDrinksLocalDB(drink: Drink): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                drinkDao.insertDrink(drink)
                Timber.e("Successfully inserted drink")
                true
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        }
    }

    override suspend fun removeDrinkById(drinkId: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                if (drinkId.isNotEmpty()) {
                    drinkDao.deleteDrinkById(drinkId)
                    true
                } else {
                    Timber.e("Unknown Error")
                    false
                }
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        }
    }
}