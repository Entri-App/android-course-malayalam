package com.example.myapplication

import android.R.layout
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.LayoutToastBinding


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
        binding.btnShowToast.setOnClickListener {
            //TODO : Create Toast
            val toast = Toast(this)
            //TODO : Set Layout
            val binding = LayoutToastBinding.inflate(layoutInflater)
            toast.view = binding.root
            toast.duration = Toast.LENGTH_LONG
            //TODO : Show Toast
            toast.show()
        }
    }
}