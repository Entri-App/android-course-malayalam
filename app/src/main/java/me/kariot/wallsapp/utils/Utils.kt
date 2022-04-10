package me.kariot.wallsapp.utils

import android.app.Activity
import android.util.DisplayMetrics


object Utils {
    fun getDeviceWidth(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
    fun getDeviceHeight(activity: Activity): Int {
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        return displayMetrics.heightPixels
    }
}