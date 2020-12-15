package brown.jeff.cocktailapp.data.repository

import brown.jeff.cocktailapp.data.entities.DrinkEntity
import brown.jeff.cocktailapp.data.db.dao.DrinkDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class FavoriteDrinkRepositoryImpl(
    private val drinkDao: DrinkDao
) : FavoriteDrinkRepository {



    override suspend fun getDrinksLocalDB(): List<DrinkEntity> {
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

    override suspend fun insertDrinksLocalDB(drinkEntity: DrinkEntity): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                drinkDao.insertDrink(drinkEntity)
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