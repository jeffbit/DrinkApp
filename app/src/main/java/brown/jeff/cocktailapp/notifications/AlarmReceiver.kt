package brown.jeff.cocktailapp.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            NotificationHelper.createNotificationForHappyHour(
                it,
                "It's 5 O'Clock Somewhere",
                "Check out our featured cocktail",
                true
            )
        }
    }




}