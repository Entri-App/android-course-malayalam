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

//        val bundle = intent.extras
//        val username = bundle?.getString(ID_USERNAME)
//        val password = bundle?.getString(ID_PASSWORD)
        val username = intent.getStringExtra(ID_USERNAME)
        val password = intent.getStringExtra(ID_PASSWORD)
        binding.txtUserInfo.text = "Username : $username \n Password : $password"

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