package com.example.myapplication

import android.Manifest
import android.R.attr
import android.R.attr.dial
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
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.util.Patterns
import android.util.Patterns.*
import com.example.myapplication.databinding.LayoutDialogBinding


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
        binding.btnShowAlert.setOnClickListener {

            val dialog = Dialog(this)

            val dialogViewBinding = LayoutDialogBinding.inflate(layoutInflater)
            dialogViewBinding.btnCancel.setOnClickListener {
                Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialogViewBinding.btnOk.setOnClickListener {
                Toast.makeText(this, "OK clicked", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            dialog.setContentView(dialogViewBinding.root)
            dialog.setCancelable(false)
            dialog.show()
        }
    }
}