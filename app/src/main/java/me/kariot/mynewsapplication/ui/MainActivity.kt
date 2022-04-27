package me.kariot.mynewsapplication.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import me.kariot.mynewsapplication.R
import me.kariot.mynewsapplication.databinding.LayoutContactUsAlertBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.contact_us) {
            showContactUsAlert()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showContactUsAlert() {
        AlertDialog.Builder(this).apply {
            setView(LayoutContactUsAlertBinding.inflate(layoutInflater).root)
        }.create().show()
    }
}