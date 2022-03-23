package me.kariot.mynewsapplication.service

import android.app.Notification
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import me.kariot.mynewsapplication.R

const val TAG = "NewsApp"
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val title = message.notification?.title ?: ""
        val body = message.notification?.body ?: ""

        val CHANNEL_ID = "NEWS_NOTIFICATION"
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_article)
                .setAutoCancel(false)
        } else {
            Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.ic_article)
                .setAutoCancel(false)
        }
        NotificationManagerCompat.from(this).notify(1, notification.build())

    }
}