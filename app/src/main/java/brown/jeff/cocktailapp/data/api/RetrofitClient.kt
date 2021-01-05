package brown.jeff.cocktailapp.data.api

import brown.jeff.cocktailapp.BuildConfig.DEBUG
import brown.jeff.cocktailapp.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private val okHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(interceptor)
//if applicaiton is in debug mode it will log data
            if (DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }
        }
        .build()


    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cocktailApi: DrinkApi = retrofit().create(DrinkApi::class.java)
}