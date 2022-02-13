package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityDashbaordBinding

class DashbaordActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashbaordBinding
    private var callReceiver: CallReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashbaordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        requestPermission()
        initBR()
    }

    private fun initBR() {
        callReceiver = CallReceiver { status ->
            binding.textView.text = status
        }
        registerReceiver(callReceiver, IntentFilter("android.intent.action.PHONE_STATE"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(callReceiver)
    }

    private fun requestPermission() {
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Permission Available", Toast.LENGTH_SHORT).show()
            }
        }.launch(Manifest.permission.READ_PHONE_STATE)
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