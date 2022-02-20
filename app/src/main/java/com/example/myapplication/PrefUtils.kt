package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences

object PrefUtils {

    private val AUTH_PREF_NAME = "auth_pref"
    private val ID_USERNAME = "USERNAME"
    private val ID_PASSWORD = "PASSWORD"


    private fun getSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(AUTH_PREF_NAME, 0)
    }

    fun saveLoginInfo(context: Context, username: String, password: String) {
        //get shared pref
        val sharedPref = getSharedPref(context)
        //create editor
        val editor = sharedPref.edit()
        //add data
        editor.putString(ID_USERNAME, username)
        editor.putString(ID_PASSWORD, password)
        //save changes
        editor.commit()
    }

    fun getUsername(context: Context): String {
        val sharedPref = getSharedPref(context)
        return sharedPref.getString(ID_USERNAME, null) ?: ""
    }

    fun getPassword(context: Context): String {
        val sharedPref = getSharedPref(context)
        return sharedPref.getString(ID_PASSWORD, null) ?: ""
    }

}