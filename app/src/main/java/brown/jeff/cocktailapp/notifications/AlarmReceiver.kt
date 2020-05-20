package brown.jeff.cocktailapp.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar

class AlarmReceiver() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            NotificationHelper.createNotificationForHappyHour(
                it,
                "It's 5'0clock somewhere",
                "Check out a random Drink",
                true
            )
        }
    }
}