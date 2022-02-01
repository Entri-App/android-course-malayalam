package com.example.myapplication

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
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
        binding.btnShowMenu.setOnClickListener {
            val menu = PopupMenu(this, it)
            menu.inflate(R.menu.main_menu)
            menu.setOnMenuItemClickListener(menuCallback)
            menu.show()
        }
    }

    val menuCallback = PopupMenu.OnMenuItemClickListener {
        when (it.itemId) {
            R.id.menu_settings -> {
                Toast.makeText(this, "menu_settings clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_delete -> {
                Toast.makeText(this, "menu_delete clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_edit -> {
                Toast.makeText(this, "menu_edit clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_search -> {
                Toast.makeText(this, "menu_search clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_profile -> {
                Toast.makeText(this, "menu_profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_about -> {
                Toast.makeText(this, "menu_about clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_contact -> {
                Toast.makeText(this, "menu_contact clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_legal -> {
                Toast.makeText(this, "menu_legal clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return@OnMenuItemClickListener true
    }
}