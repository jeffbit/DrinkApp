package brown.jeff.cocktailapp.util

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.ui.MainActivity

private const val NOTIFICATION_ID: Int = 123

fun NotificationManager.sendNotification(applicationContext: Context) {

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


    notify(NOTIFICATION_ID, builder.build())
}