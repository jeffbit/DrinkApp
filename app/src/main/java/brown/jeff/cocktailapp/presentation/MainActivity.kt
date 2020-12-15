package brown.jeff.cocktailapp.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.util.notification.AlarmReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    private lateinit var alarmReceiver: AlarmManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //sets alarm to be enabled
        enableAlarm()

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

    }


    private fun enableAlarm() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 13)
        calendar.set(Calendar.MINUTE, 5)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val currentHour = System.currentTimeMillis()
        if (currentHour <= calendar.timeInMillis) {
            alarmReceiver = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, AlarmReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)



            alarmReceiver.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )


        }

    }




}
