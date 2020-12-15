package brown.jeff.cocktailapp.repositories

import brown.jeff.cocktailapp.model.Drinks
import brown.jeff.cocktailapp.network.Result

interface DrinkRepository {


    suspend fun getDrinksByName(drinkName: String): Result<Drinks>

    suspend fun getDrinkById(drinkId: String): Result<Drinks>

    suspend fun getDrinksByIngredients(ingredients: String?): Result<Drinks>

    suspend fun getPopularDrinks(): Result<Drinks>

    suspend fun getRandomDrink(): Result<Drinks>

}