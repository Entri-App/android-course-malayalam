package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
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

    private fun setupUI() {

        binding.checkBoxMalayalam.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Malayalam is checked", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Malayalam is unchecked", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button.setOnClickListener {
            if (binding.checkBoxMalayalam.isChecked) {
                Toast.makeText(this, "Malayalam is known", Toast.LENGTH_SHORT).show()
            }
            if (binding.checkBoxEnglish.isChecked) {
                Toast.makeText(this, "English is known", Toast.LENGTH_SHORT).show()
            }
            if (binding.checkBoxHindi.isChecked) {
                Toast.makeText(this, "Hindi is known", Toast.LENGTH_SHORT).show()
            }
            if (binding.checkBoxOthers.isChecked) {
                Toast.makeText(this, "Knows other languages", Toast.LENGTH_SHORT).show()
            }


        }
    }
}