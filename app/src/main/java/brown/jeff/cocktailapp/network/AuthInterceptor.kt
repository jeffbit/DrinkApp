package brown.jeff.cocktailapp.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().url().newBuilder()
            .addQueryParameter("apiKey", brown.jeff.cocktailapp.BuildConfig.apiKey).build()

        val request = chain.request().newBuilder().url(newRequest).build()
        return chain.proceed(request)
    }

}


