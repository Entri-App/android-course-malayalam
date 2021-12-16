package com.example.myapplication

import android.os.Bundle
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

    fun setupUI() {
        binding.calendarView.maxDate = 1641018600000L
        binding.calendarView.minDate = 1609482600000L
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val date = "$year/${month + 1}/$dayOfMonth"
            binding.textView.text = date
        }
    }


}