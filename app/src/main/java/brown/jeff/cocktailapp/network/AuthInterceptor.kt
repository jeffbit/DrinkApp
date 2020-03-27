package brown.jeff.cocktailapp.network

import brown.jeff.cocktailapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

//cant figure out how to add apikey into url, it doesnt take any parameters
//if i use .addpath it puts it at the end of the url when it needs to be placed before the get call portion of the url

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().url().newBuilder()
            .addQueryParameter("", BuildConfig.apiKey).build()

        val request = chain.request().newBuilder().url(newRequest).build()
        return chain.proceed(request)
    }

}


