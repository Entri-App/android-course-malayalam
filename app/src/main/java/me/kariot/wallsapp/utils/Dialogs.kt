package me.kariot.wallsapp.utils

import android.app.Activity
import android.app.AlertDialog

object Dialogs {

    fun askConfirmation(activity: Activity, onClickYes: () -> Unit, onClickNo: () -> Unit) {
        val builder = AlertDialog.Builder(activity).apply {
            setTitle("Update lock screen wallpaper?")
            setMessage("Do you want to update lockscreen wallpaper?")
            setPositiveButton("Yes") { p0, p1 ->
                onClickYes()
                p0.dismiss()
            }
            setNegativeButton("No") { dialog, p1 ->
                onClickNo()
                dialog.dismiss()
            }
            setNeutralButton("Cancel", null)
        }.show()
    }

}