package com.example.myapplication

import android.os.Build
import android.os.Bundle
import android.view.Gravity
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

    fun setupUI() {
        binding.btnShowToast.setOnClickListener {
            val toast = Toast.makeText(this, "Simple Toast", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.BOTTOM or Gravity.RIGHT, 0, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                toast.addCallback(object : Toast.Callback() {
                    override fun onToastShown() {
                        super.onToastShown()
                    }

                    override fun onToastHidden() {
                        super.onToastHidden()
                    }
                })
            }
            toast.show()
        }
    }
}