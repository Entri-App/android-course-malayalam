package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val username = intent.getStringExtra("USERNAME")
        val password = intent.getStringExtra("PASSWORD")

        binding.txtUserInfo.text = "Username is $username \nPassword is $password"
    }
}