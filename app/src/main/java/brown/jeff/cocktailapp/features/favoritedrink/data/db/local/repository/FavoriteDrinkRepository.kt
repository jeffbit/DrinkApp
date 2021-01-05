package brown.jeff.cocktailapp.features.favoritedrink.data.db.local.repository

import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.model.DrinkORM

interface FavoriteDrinkRepository {


    suspend fun getDrinksLocalDB(): List<DrinkORM>

    suspend fun deleteAllDrinks()

    suspend fun insertDrinksLocalDB(drink: DrinkORM): Boolean

    suspend fun removeDrinkById(drinkId: String): Boolean
}