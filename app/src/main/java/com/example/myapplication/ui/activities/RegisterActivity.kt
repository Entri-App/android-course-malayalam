package com.example.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRegisterBinding

const val TAG = "EntriApp"

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