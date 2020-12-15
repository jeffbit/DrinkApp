package brown.jeff.cocktailapp.repositories

import brown.jeff.cocktailapp.model.Drink

interface FavoriteDrinkRepository {


    suspend fun getDrinksLocalDB(): List<Drink>

    suspend fun deleteAllDrinks()

    suspend fun insertDrinksLocalDB(drink: Drink): Boolean

    suspend fun removeDrinkById(drinkId: String): Boolean
}