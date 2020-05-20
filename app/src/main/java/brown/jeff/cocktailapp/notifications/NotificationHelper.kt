package brown.jeff.cocktailapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.ui.MainActivity


private val CHANNEL_ID: String = "ChannelID"

object NotificationHelper {

    fun createNotificationChannel(
        context: Context,
        importance: Int,
        showBadge: Boolean,
        name: String,
        descriptionText: String
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            channel.setShowBadge(showBadge)

            val notificationManager =
                context.getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)


        }


    }


    fun createNotificationForHappyHour(
        context: Context,
        title: String,
        description: String,
        autoCancel: Boolean
    ) {

//        val snoozeIntent: Intent = Intent(context, MainActivity::class.java).apply {
//            action = "SNOOZE"
//            putExtra("1", 0)
//        }
//
//        val snoozePendingIntent: PendingIntent =
//            PendingIntent.getBroadcast(context, 0, snoozeIntent, 0)

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.navigation_random)
            .createPendingIntent()
        val builder = NotificationCompat.Builder(
            context,
            CHANNEL_ID

        )
            //change small icon to cup
            .setSmallIcon(R.drawable.ic_broken_image_black_24dp)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //closes notification after click
            .setAutoCancel(autoCancel)
            .setContentIntent(pendingIntent)
//            .addAction(R.drawable.ic_favorite_black_24dp, "SNOOZE", snoozePendingIntent)


        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1001, builder.build())
    }

}