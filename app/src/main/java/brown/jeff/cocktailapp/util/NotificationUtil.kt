package brown.jeff.cocktailapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavDeepLinkBuilder
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.ui.MainActivity

private const val NOTIFICATION_ID: Int = 123

fun sendNotification(applicationContext: Context) {
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    createNotificationChannel(notificationManager)
    notificationManager.sendNotification(this)

    val intent: Intent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = NavDeepLinkBuilder(applicationContext)
        .setGraph(R.navigation.mobile_navigation)
        .setDestination(R.id.navigation_random)
        .createPendingIntent()
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.drink_notification_channel_id)
    )
        //change small icon to cup
        .setSmallIcon(R.drawable.ic_broken_image_black_24dp)
        .setContentTitle("Its 5'oclock somewhere!")
        .setContentText("Come check out our drinks")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        //closes notification after click
        .setAutoCancel(true)
        .setContentIntent(pendingIntent)
    // possibly add image icon of random drink to be displayed


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

    notify(NOTIFICATION_ID, builder.build())
}