package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.btnLogin.setOnClickListener {

            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            if (username.isBlank() || username.length < 5) {
                Toast.makeText(this, "Please enter a valid username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isBlank() || password.length < 5) {
                Toast.makeText(this, "Please enter a valid password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, DashbaordActivity::class.java)
            intent.putExtra("USERNAME", username)
            intent.putExtra("PASSWORD", password)
            startActivity(intent)
        }
    }
}