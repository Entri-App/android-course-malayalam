package me.kariot.wallsapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest

object ImageUtils {
    fun urlToBitmap(
        context: Context,
        url: String,
        success: (Bitmap) -> Unit,
        error: (String) -> Unit
    ) {
        val imageLoader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .target(
                onSuccess = { result ->
                    val bitmap = (result as BitmapDrawable).bitmap
                    success(bitmap)
                },
                onError = { _ ->
                    error("Failed to download image..!")
                }
            )
            .build()
        imageLoader.enqueue(request)
    }

}