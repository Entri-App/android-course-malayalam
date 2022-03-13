package me.kariot.mynewsapplication.extensions

import android.widget.ImageView
import coil.load
import coil.transform.RoundedCornersTransformation
import me.kariot.mynewsapplication.R

fun ImageView.loadImage(url: String) {
    this.load(url) {
        crossfade(true)
        placeholder(R.mipmap.ic_launcher)
        transformations(RoundedCornersTransformation(10f))
    }
}