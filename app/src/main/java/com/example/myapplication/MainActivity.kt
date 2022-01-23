package com.example.myapplication

import android.Manifest
import android.R.attr
import android.app.Activity
import android.content.Intent
import android.content.Intent.*
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import android.R.attr.phoneNumber


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
    }

    fun setupUI() {
        binding.btnDial.setOnClickListener {
            val phone = binding.txtPhone.text.toString()
            if (phone.isBlank() || phone.length < 6) {
                binding.txtPhone.error = "Invalid phone"
                return@setOnClickListener
            }

            val messageBody = binding.txtMsg.text.toString()
            val intent = Intent(ACTION_VIEW, Uri.parse("smsto:$phone"))
            intent.putExtra("sms_body",messageBody)
            startActivity(intent)

        }
    }
}