package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
        binding.button.setOnClickListener {
                                                // true
            binding.progressBar4.isVisible = !(binding.progressBar4.isVisible)
            binding.progressBar5.isVisible = !(binding.progressBar5.isVisible)

            val progress = binding.progressBar6.progress
            val maxProgress = binding.progressBar6.max
            if (progress < maxProgress) {
                binding.progressBar6.progress = progress + 10
            } else {
                binding.progressBar6.progress = 0
            }
            binding.textView.text = "${binding.progressBar6.progress}/$maxProgress"
        }

    }

}