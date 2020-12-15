package brown.jeff.cocktailapp.network

import brown.jeff.cocktailapp.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private val okHttpClient = OkHttpClient.Builder()
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cocktailApi: DrinkApi = retrofit().create(DrinkApi::class.java)
}