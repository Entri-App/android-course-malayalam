package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
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

        //TODO : Define Data
        val user = getData()
        //TODO : Create custom Adapter

        // TODO : Set adapter to listview

    }

    private fun getData(): ArrayList<UserData> {

        val data = ArrayList<UserData>()
        data.add(UserData("Sreehari K", "123 456 789 0", R.drawable.avatar))
        data.add(UserData("John Dow", "567 456 789 8", R.drawable.avatar))
        data.add(UserData("Custom Name", "368 567 789 0", R.drawable.avatar))
        data.add(UserData("New Friend", "456 456 123 1", R.drawable.avatar))
        data.add(UserData("Random Guy", "132 354 789 6", R.drawable.avatar))
        data.add(UserData("Random Guy #2", "123 890 789 9", R.drawable.avatar))
        data.add(UserData("Ramu", "897 423 789 8", R.drawable.avatar))
        data.add(UserData("Damu", "123 678 456 0", R.drawable.avatar))
        return data

    }
}