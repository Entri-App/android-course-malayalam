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

            var array = intArrayOf(1, 2, 3, 4, 5, 6)

            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            val intent = Intent(this, DashbaordActivity::class.java)
            intent.putExtra("AGE", 18.0)
            intent.putExtra("ARRAY", array)

            val userObj = UserInfo(username, password)
            intent.putExtra("USER_OBJ", userObj)

            startActivity(intent)
        }
    }
}