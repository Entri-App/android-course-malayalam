package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
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
        val sharedPref = getEncSharedPref()
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

            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            //get shared pref
            val sharedPref = getEncSharedPref()
            //create editor
            val editor = sharedPref.edit()
            //add data
            editor.putString("USERNAME", username)
            editor.putString("PASSWORD", password)
            //save changes
            editor.commit()

        }
    }

    private fun getEncSharedPref(): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "secret_shared_prefs",
            masterKeyAlias,
            this,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }
}