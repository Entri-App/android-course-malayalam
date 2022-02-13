package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast
import java.lang.Exception

class CallReceiver(
    private var callback: (String) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                val num = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                callback("Phone is ringing")
            }
            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                callback("Call received")
            }
            if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                callback("Phone is Idle")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}