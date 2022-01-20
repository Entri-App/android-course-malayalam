package com.example.myapplication

import android.Manifest
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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var request: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
    }

    fun setupUI() {

        request =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    val phone = binding.txtPhone.text.toString()
                    val uri = "tel:+91$phone"
                    val intent = Intent(ACTION_CALL, Uri.parse(uri))
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Permission is missing", Toast.LENGTH_SHORT).show()
                }
            }

        binding.btnCall.setOnClickListener {
            val phone = binding.txtPhone.text.toString()
            if (phone.isBlank() || phone.length < 8) {
                binding.txtPhone.error = "Invalid Number"
                return@setOnClickListener
            }
            request.launch(Manifest.permission.CALL_PHONE)

        }
    }
}