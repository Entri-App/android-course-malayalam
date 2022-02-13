package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.dsl.extension.requestPermissions
import com.example.myapplication.databinding.ActivityDashbaordBinding

class DashbaordActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashbaordBinding
    var smsReceiver: SMSReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashbaordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()

    }


    private fun setupUI() {

    }

    private fun requestPermission(onPermissionGranted: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                Manifest.permission.RECEIVE_SMS,
            ) {
                requestCode = 4
                resultCallback = {
                    when (this) {
                        is PermissionResult.PermissionGranted -> {
                            onPermissionGranted()
                        }
                        is PermissionResult.PermissionDenied -> {
                            Toast.makeText(
                                this@DashbaordActivity,
                                "Permission Denied",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is PermissionResult.PermissionDeniedPermanently -> {
                            Toast.makeText(
                                this@DashbaordActivity,
                                "Permission Permanently Denied",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is PermissionResult.ShowRational -> {
                            Toast.makeText(
                                this@DashbaordActivity,
                                "Permission Denied, show rational and re-ask permission",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    private fun initBR() {
        smsReceiver = SMSReceiver { sender, content ->
            binding.txtSender.text = sender
            binding.txtContent.text = content
        }
        registerReceiver(smsReceiver, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))
    }

    override fun onStart() {
        super.onStart()
        requestPermission {
            initBR()
        }
    }

    override fun onStop() {
        super.onStop()
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
        }
    }


    companion object {
        val ID_USERNAME = "USERNAME"
        val ID_PASSWORD = "PASSWORD"

        fun start(context: Context, username: String, password: String) {
            val intent = getIntent(context)
            intent.putExtra(ID_USERNAME, username)
            intent.putExtra(ID_PASSWORD, password)
            intent.putExtra(ID_PASSWORD, password)
            context.startActivity(intent)
        }

        private fun getIntent(context: Context) = Intent(context, DashbaordActivity::class.java)
    }
}