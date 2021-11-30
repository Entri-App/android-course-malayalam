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

        /*binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioFemale -> Toast.makeText(this, "Female Button is Checked", Toast.LENGTH_SHORT).show()
                R.id.radioMale -> Toast.makeText(this, "Male Button is Checked", Toast.LENGTH_SHORT).show()
                R.id.radioOthers -> Toast.makeText(this, "Others Button is Checked", Toast.LENGTH_SHORT).show()
            }
        }*/

        binding.radioFemale.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Female Button is Checked", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Female Button is Unchecked", Toast.LENGTH_SHORT).show()
            }
        }

        binding.button.setOnClickListener {

          /*  if (binding.radioFemale.isChecked) {
                Toast.makeText(this, "Female Button is Checked", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.radioMale.isChecked) {
                Toast.makeText(this, "Male Button is Checked", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (binding.radioOthers.isChecked) {
                Toast.makeText(this, "Others Button is Checked", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }*/
        }
    }
}