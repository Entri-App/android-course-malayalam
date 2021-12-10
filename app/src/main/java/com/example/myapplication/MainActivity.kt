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

        binding.button.setOnClickListener {
            val isSwitchOn = binding.switch1.isChecked
            if (isSwitchOn) {
                Toast.makeText(this, "Switch is On", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Switch is Off", Toast.LENGTH_SHORT).show()
            }
        }

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.textView.text = "Switch is On"
            } else {
                binding.textView.text = "Switch is Off"
            }
        }

    }

}