package com.example.myapplication

import android.R.layout
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                Toast.makeText(this, "Profile is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_search -> {
                val searchView = item.actionView as SearchView
                setupSearchView(searchView)
            }
            R.id.menu_edit -> {
                Toast.makeText(this, "Edit is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_delete -> {
                Toast.makeText(this, "Delete is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_settings -> {
                Toast.makeText(this, "Settings is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_about -> {
                Toast.makeText(this, "About is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_contact -> {
                Toast.makeText(this, "Contact us is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_legal -> {
                Toast.makeText(this, "Legal is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_others -> {
                Toast.makeText(this, "Sub menu has been opened", Toast.LENGTH_SHORT).show()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    /*fun onClickMenu(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_profile -> {
                Toast.makeText(this, "Profile is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_search -> {
                Toast.makeText(this, "Search is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_edit -> {
                Toast.makeText(this, "Edit is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_delete -> {
                Toast.makeText(this, "Delete is clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_settings -> {
                Toast.makeText(this, "Settings is clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

}