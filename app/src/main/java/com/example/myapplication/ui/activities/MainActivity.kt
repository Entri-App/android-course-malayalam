package com.example.myapplication.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.PrefUtils


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
        }


        binding.tvRegister.setOnClickListener {
            RegisterActivity.start(this)
        }
    }

    private fun checkForSharedPrefData() {

        val username = PrefUtils.getUsername(this)
        val password = PrefUtils.getPassword(this)

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
            PrefUtils.saveLoginInfo(this, username, password)
        }
    }


}