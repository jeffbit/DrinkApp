package brown.jeff.cocktailapp.repositories

import brown.jeff.cocktailapp.room.DrinkORM

interface FavoriteDrinkRepository {


    suspend fun getDrinksLocalDB(): List<DrinkORM>

    suspend fun deleteAllDrinks()

    suspend fun insertDrinksLocalDB(drink: DrinkORM): Boolean

    suspend fun removeDrinkById(drinkId: String): Boolean
}