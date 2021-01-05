package brown.jeff.cocktailapp.repositories

import brown.jeff.cocktailapp.model.DrinksEntity
import brown.jeff.cocktailapp.network.Result

interface DrinkRepository {


    suspend fun getDrinksByName(drinkName: String): Result<DrinksEntity>

    suspend fun getDrinkById(drinkId: String): Result<DrinksEntity>

    suspend fun getDrinksByIngredients(ingredients: String?): Result<DrinksEntity>

    suspend fun getPopularDrinks(): Result<DrinksEntity>

    suspend fun getRandomDrink(): Result<DrinksEntity>

}