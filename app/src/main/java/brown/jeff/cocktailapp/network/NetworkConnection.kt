package brown.jeff.cocktailapp.network

import android.content.Context
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
class NetworkConnection(private val context: Context) {

    fun isInternetAvailable(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager: ConnectivityManager = context.getSystemService(service) as ConnectivityManager
        val network = manager.activeNetworkInfo
        return (network != null) && network.isConnected


    }
}