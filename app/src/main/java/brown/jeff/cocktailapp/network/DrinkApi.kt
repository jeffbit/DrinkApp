package brown.jeff.cocktailapp.network

import brown.jeff.cocktailapp.model.DrinksEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkApi {

    @GET("popular.php")
    fun getPopularDrinks(): Call<DrinksEntity>

    @GET("search.php")
    fun searchDrinksByName(@Query("s") name: String): Call<DrinksEntity>

    @GET("filter.php")
    fun getDrinksByIngredient(@Query("i") ingredient: String?): Call<DrinksEntity>

    @GET("lookup.php")
    fun getDrinkById(@Query("i") drinkId: String): Call<DrinksEntity>

    @GET("random.php")
    fun getRandomDrink(): Call<DrinksEntity>


}
