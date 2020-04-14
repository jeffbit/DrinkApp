package brown.jeff.cocktailapp.repositories

import androidx.lifecycle.LiveData
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.model.Drinks
import brown.jeff.cocktailapp.network.DrinkApi
import brown.jeff.cocktailapp.network.Errors
import brown.jeff.cocktailapp.network.NetworkConnection
import brown.jeff.cocktailapp.network.Result
import brown.jeff.cocktailapp.network.Result.Failure
import brown.jeff.cocktailapp.network.Result.Success
import brown.jeff.cocktailapp.room.DrinkDao
import retrofit2.Call
import timber.log.Timber

class DrinkRepository(
    private val drinkApi: DrinkApi,
    private val drinkDao: DrinkDao,
    private val networkConnection: NetworkConnection
) {

    //DRINK API CALLS
    //possible to use kotlin extensions to clean class up
    //separate into 2 classes; Api calls, Room calls

    //gets list of drinks by name
    suspend fun getDrinksByName(drinkName: String): Result<Drinks> {
        return makeCall(drinkApi.searchDrinksByName(drinkName))

    }

    //gets list of drinks that start with letter
    //this will be useful if we have a dictionary view of all drinks in alphabetical order
    suspend fun getDrinksByFirstLetterInName(firstLetter: Char): Result<Drinks> {
        return makeCall(drinkApi.searchDrinksByLetter(firstLetter))
    }

    //gets single drink by drinkId
    suspend fun getDrinkById(drinkId: String): Result<Drink> {
        return makeCall(drinkApi.getDrinkById(drinkId))
    }


    //gets recently added drinks
    suspend fun getRecentDrinks(): Result<Drinks> {
        return makeCall(drinkApi.getRecentDrinks())
    }


    //gets popular drinks
    suspend fun getPopularDrinks(): Result<Drinks> {
        Timber.e("Drinks called")
        return makeCall(drinkApi.getPopularDrinks())

    }

    //gets random single drink
    suspend fun getRandomDrink(): Result<Drink> {
        return makeCall(drinkApi.getRandomDrink())


    }


    //DRINKDAO CALLS

    //TODO: Add empty or null check to return error if local database is empty.


    suspend fun getDrinksLocalDB(): LiveData<List<Drink>> {
        return drinkDao.getAllDrinks()

    }

    suspend fun insertDrinksLocalDB(drink: Drink): Boolean {
        return try {
            drinkDao.insertDrink(drink)
            true
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }


    //Call for api
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