package brown.jeff.cocktailapp.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.util.sendNotification
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    private lateinit var notificationManager: NotificationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        navView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        //hides bottom navigation view for DrinkClickedFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.drinkClickedFragment || destination.id == R.id.navigation_random) {
                navView.visibility = View.GONE
                supportActionBar?.hide()
            } else {
                navView.visibility = View.VISIBLE
                supportActionBar?.show()
            }
        }



        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(notificationManager)
        notificationManager.sendNotification(this)
    }

    fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager.getNotificationChannel(getString(R.string.drink_notification_channel_id)) == null) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        getString(R.string.drink_notification_channel_id),
                        "Default",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                )

            }
        }
    }
}
