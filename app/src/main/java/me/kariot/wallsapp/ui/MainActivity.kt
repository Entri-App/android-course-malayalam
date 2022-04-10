package me.kariot.wallsapp.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import me.kariot.wallsapp.databinding.ActivityMainBinding
import me.kariot.wallsapp.databinding.LayoutErrorBinding
import me.kariot.wallsapp.model.ModelWallpaperResponse
import me.kariot.wallsapp.presenter.WallpaperPresenter
import me.kariot.wallsapp.presenter.WallpaperView
import me.kariot.wallsapp.utils.ImageUtils

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
            val position = binding.viewPager2.currentItem
            val wallpaper = presenter.wallpapers[position]
            setWallpaper(wallpaper)
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

    private fun startImageCropper(i: Int) {

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