package brown.jeff.cocktailapp.network

import brown.jeff.cocktailapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL: String = "https://www.thecocktaildb.com/api/json/v2/${BuildConfig.apiKey}/"
   // private val authInterceptor = AuthInterceptor()

    //for debugging and viewing url
    private val httpLoggingInterceptor = kotlin.run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
//        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(httpLoggingInterceptor).build()


    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cocktailApi: DrinkApi = retrofit().create(DrinkApi::class.java)
}