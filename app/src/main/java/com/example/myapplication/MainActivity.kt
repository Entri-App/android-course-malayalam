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
        checkForSharedPrefData()
    }


    private fun setupUI() {
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            if (username.isBlank()) {
                Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isBlank()) {
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            checkSharedPref()

            DashbaordActivity.start(this, username, password)
        }


        binding.tvRegister.setOnClickListener {
            RegisterActivity.start(this)
        }
    }

    private fun checkForSharedPrefData() {
        val sharedPref = getSharedPreferences("LOGIN_INFO", MODE_PRIVATE)
        val username = sharedPref.getString("USERNAME", "")
        val password = sharedPref.getString("PASSWORD", "")

        if (!username.isNullOrBlank()) {
            binding.txtUsername.setText(username)
        }
        if (!password.isNullOrBlank()) {
            binding.txtPassword.setText(password)
        }
    }

    private fun checkSharedPref() {
        if (binding.cbSaveInfo.isChecked) {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            //get shared pref
            val sharedPref = getSharedPreferences("LOGIN_INFO", MODE_PRIVATE)
            //create editor
            val editor = sharedPref.edit()
            //add data
            editor.putString("USERNAME", username)
            editor.putString("PASSWORD", password)
            //save changes
            editor.commit()

        }
    }
}