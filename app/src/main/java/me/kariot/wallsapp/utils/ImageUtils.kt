package me.kariot.wallsapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.ImageRequest
import java.io.IOException

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

    fun saveImageToStorage(context: Context, bitmap: Bitmap, id: Int): Boolean {
        return try {
            context.openFileOutput("${id}.jpg", AppCompatActivity.MODE_PRIVATE).use { stream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)) {
                    throw IOException("Could not save image")
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

}