package com.example.myapplication.ui.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.JsonObjectRequest
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.PrefUtils
import com.example.myapplication.volley.MySingleton
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
        checkForSharedPrefData()
    }


    private fun setupUI() {
        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            if (username.isBlank()) {
                Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isBlank()) {
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val url = "https://reqres.in/api/login"

            val reqObj = JSONObject()
            reqObj.put("email", username)
            reqObj.put("password", password)

            Log.d(TAG, "JSON : $reqObj")

            val progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Please wait..")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val request = JsonObjectRequest(Request.Method.POST, url, reqObj, { response ->
                progressDialog.dismiss()
                val token = response.getString("token")
                checkSharedPref()
                DashbaordActivity.start(this, username, password)
                finish()
            }, { error ->
                progressDialog.dismiss()
                if (error.networkResponse != null) {
                    if (error.networkResponse.statusCode == 400) {
                        Toast.makeText(this, "Invalid username and password", Toast.LENGTH_SHORT)
                            .show()
                        return@JsonObjectRequest
                    }
                }
                val message = when (error) {
                    is NetworkError -> "Network error occurred"
                    is ServerError -> "Server error occurred"
                    is AuthFailureError -> "Authentication error"
                    is ParseError -> "Parse error"
                    is NoConnectionError -> "No connection."
                    is TimeoutError -> "Request timed out."
                    else -> "An error occurred"
                }
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            })
            MySingleton.getInstance(this).addToRequestQueue(request)

        }


        binding.tvRegister.setOnClickListener {
            RegisterActivity.start(this)
        }
    }

    private fun checkForSharedPrefData() {

        val username = PrefUtils.getUsername(this)
        val password = PrefUtils.getPassword(this)

        if (!username.isNullOrBlank()) {
            binding.txtUsername.setText(username)
        }
        if (!password.isNullOrBlank()) {
            binding.txtPassword.setText(password)
        }
    }

    private fun checkSharedPref() {
        if (binding.cbSaveInfo.isChecked) {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            PrefUtils.saveLoginInfo(this, username, password)
        }
    }


}