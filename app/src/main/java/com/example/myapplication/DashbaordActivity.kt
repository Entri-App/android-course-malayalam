package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityDashbaordBinding
import java.util.*

class DashbaordActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashbaordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashbaordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()

    }

    private fun setupUI() {

        val age = intent.getDoubleExtra("AGE", 20.0)
        val array = intent.getIntArrayExtra("ARRAY")
        Log.d("TAG", "setupUI: ${Arrays.toString(array)} ")

        var userObj = intent.getSerializableExtra("USER_OBJ") as? UserInfo

        binding.txtUserInfo.text =
            "Username is ${userObj?.username} \nPassword is ${userObj?.password} \nAge is $age"
    }
}