package brown.jeff.cocktailapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import brown.jeff.cocktailapp.R
import brown.jeff.cocktailapp.ui.MainActivity


private const val CHANNEL_ID: String = "ChannelID"

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
            .setSmallIcon(R.drawable.ic_whatshot_black_24dp)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            //closes notification after click
            .setAutoCancel(autoCancel)
            .setContentIntent(pendingIntent)


        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1001, builder.build())
    }

}