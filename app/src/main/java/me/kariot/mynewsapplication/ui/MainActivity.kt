package me.kariot.mynewsapplication.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import me.kariot.mynewsapplication.R
import me.kariot.mynewsapplication.service.MyFirebaseMessagingService

class MainActivity : AppCompatActivity() {
    lateinit var receiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, intent: Intent) {
                val title = intent.getStringExtra("title")
                val body = intent.getStringExtra("body")
                AlertDialog.Builder(this@MainActivity)
                    .setTitle(title)
                    .setMessage(body)
                    .setPositiveButton("Ok", null)
                    .create().show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(MyFirebaseMessagingService.INTENT_ACTION_PUSH)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}