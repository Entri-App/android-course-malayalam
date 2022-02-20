package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object PrefUtils {

    private val AUTH_PREF_NAME = "secret_shared_prefs"
    private val ID_USERNAME = "USERNAME"
    private val ID_PASSWORD = "PASSWORD"


    private fun getEncSharedPref(context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            AUTH_PREF_NAME,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        );
    }

    fun saveLoginInfo(context: Context, username: String, password: String) {
        //get shared pref
        val sharedPref = getEncSharedPref(context)
        //create editor
        val editor = sharedPref.edit()
        //add data
        editor.putString(ID_USERNAME, username)
        editor.putString(ID_PASSWORD, password)
        //save changes
        editor.commit()
    }

    fun getUsername(context: Context): String {
        val sharedPref = getEncSharedPref(context)
        return sharedPref.getString(ID_USERNAME, null) ?: ""
    }

    fun getPassword(context: Context): String {
        val sharedPref = getEncSharedPref(context)
        return sharedPref.getString(ID_PASSWORD, null) ?: ""
    }

}