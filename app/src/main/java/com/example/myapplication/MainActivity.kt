package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "Inside onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Inside onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Inside onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Inside onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Inside onDestroy")
    }

}