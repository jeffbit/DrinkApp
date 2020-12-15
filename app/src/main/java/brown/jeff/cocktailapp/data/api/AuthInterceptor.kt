package brown.jeff.cocktailapp.data.api

import brown.jeff.cocktailapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().url().newBuilder()
            .addQueryParameter("", BuildConfig.apiKey).build()

        val request = chain.request().newBuilder().url(newRequest).build()
        return chain.proceed(request)
    }

}


