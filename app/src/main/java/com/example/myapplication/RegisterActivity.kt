package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.tasks.Task

const val TAG = "RegisterActivity"

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun start(context: Context) {
            val intent = getIntent(context)
            context.startActivity(intent)
        }

        private fun getIntent(context: Context) = Intent(context, RegisterActivity::class.java)
    }

}