package brown.jeff.cocktailapp.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import brown.jeff.cocktailapp.util.sendNotification
import java.util.*


class AlarmReceiver() : BroadcastReceiver() {
    fun startAlarmBroadCastReceiver(context: Context) {
        val intent: Intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager: AlarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 12)
        calendar.set(Calendar.MINUTE, 20)
        calendar.set(Calendar.SECOND, 0)

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)


    }

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { sendNotification(it) }
    }


}