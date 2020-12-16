package brown.jeff.cocktailapp.network

import androidx.lifecycle.LiveData
import brown.jeff.cocktailapp.model.Drinks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {

    @GET("popular.php")
    fun getPopularDrinks(): Call<Drinks>

    @GET("search.php")
    fun searchDrinksByName(@Query("s") name: String): Call<Drinks>

    @GET("filter.php")
    fun getDrinksByIngredient(@Query("i") ingredient: String?): Call<Drinks>

    @GET("lookup.php")
    fun getDrinkById(@Query("i") drinkId: String): Call<Drinks>

    @GET("random.php")
    fun getRandomDrink(): Call<Drinks>


}
