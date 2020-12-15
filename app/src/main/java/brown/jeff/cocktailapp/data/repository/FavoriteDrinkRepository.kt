package brown.jeff.cocktailapp.data.repository

import brown.jeff.cocktailapp.data.entities.DrinkEntity

interface FavoriteDrinkRepository {


    suspend fun getDrinksLocalDB(): List<DrinkEntity>

    suspend fun deleteAllDrinks()

    suspend fun insertDrinksLocalDB(drinkEntity: DrinkEntity): Boolean

    suspend fun removeDrinkById(drinkId: String): Boolean
}