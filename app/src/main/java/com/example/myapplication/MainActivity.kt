package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
    }

    private fun setupUI() {

        binding.btnLogin.setOnClickListener {

            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            if (username.isBlank()){
                binding.edtUsername.setError("Invalid Username")
                return@setOnClickListener
            }
            if (password.isBlank() || password.length < 3){
                binding.edtPassword.setError("Invalid Password")
                return@setOnClickListener
            }

            Toast.makeText(
                this,
                "Username is $username && password is $password",
                Toast.LENGTH_LONG
            ).show()
        }

    }
}