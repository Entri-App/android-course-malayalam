package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        //TODO : Data source init
        val users = getData()

        //TODO : Set LayoutManager for RecyclerView
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        //TODO : Set Adapter
        val adapter = MyAdapter(users) { it, position ->
            Toast.makeText(this, "Item Clicked ${it.name}", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.adapter = adapter

        //TODO : Add divider line
        val itemDecorator = DividerItemDecoration(this, layoutManager.orientation)
        binding.recyclerView.addItemDecoration(itemDecorator)

        //TODO : Set OnclickListener


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