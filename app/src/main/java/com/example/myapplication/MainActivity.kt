package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar

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
        binding.button.setOnClickListener {
            //TODO : Show SnackBar
            Snackbar.make(binding.layout, "This is a snackbar", Snackbar.LENGTH_LONG).show()
        }

        binding.button2.setOnClickListener {
            Snackbar.make(binding.layout, "This is a snackbar with action", LENGTH_INDEFINITE)
                .setAction("DISMISS") {
                    Toast.makeText(this, "Snackbar dismissed!!", Toast.LENGTH_SHORT).show()
                }
                .show()
        }
    }

}