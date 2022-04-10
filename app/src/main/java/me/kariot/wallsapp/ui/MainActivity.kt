package me.kariot.wallsapp.ui

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import com.google.android.material.snackbar.Snackbar
import me.kariot.wallsapp.databinding.ActivityMainBinding
import me.kariot.wallsapp.databinding.LayoutErrorBinding
import me.kariot.wallsapp.model.ModelWallpaperResponse
import me.kariot.wallsapp.presenter.WallpaperPresenter
import me.kariot.wallsapp.presenter.WallpaperView
import me.kariot.wallsapp.utils.Dialogs
import me.kariot.wallsapp.utils.ImageUtils
import me.kariot.wallsapp.utils.Utils


const val TAG = "WallsApp"

class MainActivity : AppCompatActivity(), WallpaperView {

    lateinit var presenter: WallpaperPresenter
    lateinit var binding: ActivityMainBinding
    lateinit var errorBinding: LayoutErrorBinding
    lateinit var adapter: ImagesAdapter

    private var isLoading = false
    private var isFullyLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        errorBinding = LayoutErrorBinding.bind(binding.error.root)
        setContentView(binding.root)
        initUI()
        presenter = WallpaperPresenter(this)
        presenter.loadPictures()
    }

    private fun initUI() {
        adapter = ImagesAdapter {
            if (!isLoading && !isFullyLoaded) {
                presenter.getNextPage()
            }
        }
        binding.viewPager2.adapter = adapter
        errorBinding.btnRetry.setOnClickListener {
            presenter.loadPictures()
        }
        binding.fabSetWall.setOnClickListener {
            if (presenter.wallpapers.isEmpty()) {
                return@setOnClickListener
            }
            val position = binding.viewPager2.currentItem
            val wallpaper = presenter.wallpapers[position]
            setWallpaper(wallpaper)
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            adapter.wallpapers.clear()
            adapter.notifyDataSetChanged()
            presenter.clearData()
            presenter.loadPictures()
        }
    }

    private fun setWallpaper(wallpaper: ModelWallpaperResponse.Photo?) {
        if (wallpaper == null) {
            return
        }
        ImageUtils.urlToBitmap(this, wallpaper.src?.original ?: "", { bitmap ->
            val isSaveSuccess = ImageUtils.saveImageToStorage(this, bitmap, wallpaper.id ?: 0)
            if (isSaveSuccess) {
                startImageCropper(wallpaper.id ?: 0)
            }

        }, { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })


    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            val uriContent = result.uriContent
            uriContent?.let {

                val bitmap = when {
                    Build.VERSION.SDK_INT < 28 -> MediaStore.Images.Media.getBitmap(
                        this.contentResolver,
                        uriContent
                    )
                    else -> {
                        val source = ImageDecoder.createSource(this.contentResolver, uriContent)
                        ImageDecoder.decodeBitmap(source)
                    }
                }
                updateDeviceWallpaper(bitmap)
            }

        } else {
            // an error occurred
            val exception = result.error
        }
    }

    private fun updateDeviceWallpaper(bitmap: Bitmap?) {
        val wallpaperManager = WallpaperManager.getInstance(this)
        bitmap?.let { image ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Dialogs.askConfirmation(this, {
                    wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_SYSTEM)
                    wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_LOCK)
                    Toast.makeText(this@MainActivity, "Wallpaper updated", Toast.LENGTH_SHORT)
                        .show()
                }, {
                    wallpaperManager.setBitmap(image, null, true, WallpaperManager.FLAG_SYSTEM)
                    Toast.makeText(this@MainActivity, "Wallpaper updated", Toast.LENGTH_SHORT)
                        .show()
                })
            } else {
                wallpaperManager.setBitmap(image)
            }

        }
    }

    private fun startImageCropper(id: Int) {
        val imageUri = ImageUtils.getImageUri(this, id)
        cropImage.launch(
            options(uri = imageUri) {
                setGuidelines(CropImageView.Guidelines.ON)
                setOutputCompressFormat(Bitmap.CompressFormat.PNG)
                    .setAspectRatio(
                        Utils.getDeviceWidth(this@MainActivity),
                        Utils.getDeviceHeight(this@MainActivity)
                    )
            }
        )
    }

    override fun isLoading() {
        isLoading = true
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun onWallpaperLoaded(
        wallapapers: List<ModelWallpaperResponse.Photo?>?,
        totalItems: Int
    ) {
        isLoading = false
        binding.swipeRefreshLayout.isRefreshing = false
        errorBinding.layError.isVisible = false
        wallapapers?.let {
            adapter.addData(it)
        }
        isFullyLoaded = adapter.itemCount == totalItems
        Log.d(TAG, "onWallpaperLoaded: $totalItems")
    }

    override fun error(message: String, isFirstRequest: Boolean) {
        isLoading = false
        binding.swipeRefreshLayout.isRefreshing = false
        if (isFirstRequest) {
            errorBinding.layError.isVisible = true
            errorBinding.tvError.text = message
            return
        }
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).setAction("RETRY") {
            presenter.loadPictures()
        }.show()
    }
}