package brown.jeff.cocktailapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val releaseUrl: String = "https://www.thecocktaildb.com/api/json/v2"
    private val authInterceptor = AuthInterceptor()


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor).build()

    private fun retrofit(): Retrofit {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient).build()

        return retrofit()
    }

    val cocktailApi: DrinkApi = retrofit().create(DrinkApi::class.java)
}