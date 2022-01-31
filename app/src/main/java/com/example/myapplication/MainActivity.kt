package com.example.myapplication

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var actionMode: ActionMode? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupUI()
    }

    fun setupUI() {
        registerForContextMenu(binding.tvFloating)
        binding.tvActionMode.setOnLongClickListener {
            if (actionMode != null) {
                return@setOnLongClickListener false
            }
            actionMode = startActionMode(actionModeCallback)
            return@setOnLongClickListener true
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflator = menuInflater
        inflator.inflate(R.menu.main_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_profile -> {
                Toast.makeText(this@MainActivity, "menu_profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_delete -> {
                Toast.makeText(this@MainActivity, "menu_delete clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_edit -> {
                Toast.makeText(this@MainActivity, "menu_edit clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_settings -> {
                Toast.makeText(this@MainActivity, "menu_settings clicked", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> return super.onContextItemSelected(item)
        }
        return true

    }


    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            val inflater = mode.menuInflater
            inflater.inflate(R.menu.main_menu, menu)
            mode.title = "Choose an action"
            return true
        }

        override fun onPrepareActionMode(p0: ActionMode?, p1: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(p0: ActionMode?, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.menu_profile -> {
                    Toast.makeText(this@MainActivity, "menu_profile clicked", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                R.id.menu_delete -> {
                    Toast.makeText(this@MainActivity, "menu_delete clicked", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                R.id.menu_edit -> {
                    Toast.makeText(this@MainActivity, "menu_edit clicked", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                R.id.menu_settings -> {
                    Toast.makeText(this@MainActivity, "menu_settings clicked", Toast.LENGTH_SHORT)
                        .show()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(p0: ActionMode?) {
            actionMode = null
        }

    }

}