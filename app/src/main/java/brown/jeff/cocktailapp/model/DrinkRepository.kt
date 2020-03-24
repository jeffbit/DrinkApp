package brown.jeff.cocktailapp.model

import androidx.lifecycle.LiveData
import brown.jeff.cocktailapp.network.DrinkApi
import brown.jeff.cocktailapp.network.Errors
import brown.jeff.cocktailapp.network.NetworkConnection
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.network.Result.Failure
import brown.jeff.cocktailapp.network.Result.Success
import brown.jeff.cocktailapp.room.DrinkDao
import retrofit2.Call
import timber.log.Timber
import java.lang.Exception

class DrinkRepository(
    private val drinkApi: DrinkApi,
    private val drinkDao: DrinkDao,
    private val networkConnection: NetworkConnection
) {


    suspend fun getRecentDrinks(): Result<Drinks> {
        return makeCall(drinkApi.getRecentDrinks())
    }


    suspend fun getPopularDrinks(): Result<Drinks> {
        return makeCall(drinkApi.getPopularDrinks())

    }

    suspend fun getRandomDrink(): Result<Drinks> {
        return makeCall(drinkApi.getRandomDrink())


    }


    suspend fun getDrinksLocalDB(): LiveData<List<Drink>> {
        return drinkDao.getAllDrinks()
    }

    suspend fun insertDrinksLocalDB(drink: Drink) {
        try {


            drinkDao.insertDrink(drink)
        }catch (e: Exception){
            Timber.e(e)
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