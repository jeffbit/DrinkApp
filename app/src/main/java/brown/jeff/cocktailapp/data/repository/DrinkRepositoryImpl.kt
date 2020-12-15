package brown.jeff.cocktailapp.data.repository

import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.model.Drinks
import brown.jeff.cocktailapp.network.DrinkApi
import brown.jeff.cocktailapp.network.Errors
import brown.jeff.cocktailapp.network.NetworkConnection
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.network.Result.Failure
import brown.jeff.cocktailapp.network.Result.Success
import brown.jeff.cocktailapp.room.DrinkDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import timber.log.Timber

class DrinkRepositoryImpl(
    private val drinkApi: DrinkApi,
    private val networkConnection: NetworkConnection
) : DrinkRepository {


    override suspend fun getDrinksByName(drinkName: String): Result<Drinks> {
        return withContext(Dispatchers.IO) {
            makeCall(drinkApi.searchDrinksByName(drinkName))
        }
    }


    override suspend fun getDrinkById(drinkId: String): Result<Drinks> {
        return withContext(Dispatchers.IO) {
            makeCall(drinkApi.getDrinkById(drinkId))
        }
    }

    override suspend fun getDrinksByIngredients(ingredients: String?): Result<Drinks> {
        return withContext(Dispatchers.IO) {
            makeCall(drinkApi.getDrinksByIngredient(ingredients))
        }
    }

    override suspend fun getPopularDrinks(): Result<Drinks> {
        Timber.e("Drinks called")
        return withContext(Dispatchers.IO) {
            makeCall(drinkApi.getPopularDrinks())
        }
    }

    override suspend fun getRandomDrink(): Result<Drinks> {
        return withContext(Dispatchers.IO) {
            makeCall(drinkApi.getRandomDrink())
        }
    }

    private fun <T> makeCall(call: Call<T>): Result<T> {
        return when (networkConnection.isInternetAvailable()) {
            true -> {
                try {
                    val response = call.execute()
                    when (response.isSuccessful && response.body() != null) {
                        true -> {
                            Success(response.body()!!)
                        }
                        false -> {
                            Failure(Errors.ResponseError(response.errorBody().toString()))
                        }
                    }
                } catch (e: java.lang.Exception) {
                    Failure(Errors.ExceptionError(e))
                }
            }
            false -> {
                Failure(Errors.NetworkError())
            }
        }
    }


}