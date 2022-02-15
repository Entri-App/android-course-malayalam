package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDashbaordBinding

class DashbaordActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashbaordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashbaordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()

    }


    private fun setupUI() {

    }

    companion object {
        val ID_USERNAME = "USERNAME"
        val ID_PASSWORD = "PASSWORD"

        fun start(context: Context, username: String, password: String) {
            val intent = getIntent(context)
            intent.putExtra(ID_USERNAME, username)
            intent.putExtra(ID_PASSWORD, password)
            intent.putExtra(ID_PASSWORD, password)
            context.startActivity(intent)
        }

        private fun getIntent(context: Context) = Intent(context, DashbaordActivity::class.java)
    }
}