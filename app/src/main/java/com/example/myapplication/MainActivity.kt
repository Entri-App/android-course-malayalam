package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
    }

    private fun setupUI() {
        binding.button.setOnClickListener(this)
        /*binding.button.setOnClickListener{

        }*/

        /*binding.button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

            }
        })*/
    }

//    fun onButtonPressed(view: android.view.View) {
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
//    }

    override fun onClick(v: View?) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
    }
}