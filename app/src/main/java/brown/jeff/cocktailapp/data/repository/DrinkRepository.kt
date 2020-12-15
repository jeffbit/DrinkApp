package brown.jeff.cocktailapp.data.repository

import brown.jeff.cocktailapp.data.entities.DrinksEntity
import brown.jeff.cocktailapp.domain.usecase.Result

interface DrinkRepository {


    suspend fun getDrinksByName(drinkName: String): Result<DrinksEntity>

    suspend fun getDrinkById(drinkId: String): Result<DrinksEntity>

    suspend fun getDrinksByIngredients(ingredients: String?): Result<DrinksEntity>

    suspend fun getPopularDrinks(): Result<DrinksEntity>

    suspend fun getRandomDrink(): Result<DrinksEntity>

}