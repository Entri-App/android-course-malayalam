package me.kariot.wallsapp.utils

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import me.kariot.wallsapp.R

fun ImageView.loadImage(url: String) {
    this.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher)
        error(R.mipmap.ic_launcher)
    }
}