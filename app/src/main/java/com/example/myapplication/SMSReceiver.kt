package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast

class SMSReceiver(
    val onSmsReceived: (String, String) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val smsList = Telephony.Sms.Intents.getMessagesFromIntent(intent)
        for (smsMessage in smsList) {
            val messageContent = smsMessage.displayMessageBody
            val sender = smsMessage.originatingAddress ?: "Not Available"
            onSmsReceived(sender, messageContent)
        }

    }
}