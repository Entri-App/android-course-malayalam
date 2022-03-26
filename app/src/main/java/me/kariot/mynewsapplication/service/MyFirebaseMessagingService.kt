package me.kariot.mynewsapplication.service

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val TAG = "NewsApp"
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val title = message.notification?.title ?: ""
        val body = message.notification?.body ?: ""

        val intent = Intent().apply {
            action = INTENT_ACTION_PUSH
            putExtra("title", title)
            putExtra("body", body)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    companion object {
        const val INTENT_ACTION_PUSH = "INTENT_ACTION_PUSH"
    }
}