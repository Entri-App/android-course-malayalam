package me.kariot.wallsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import me.kariot.wallsapp.databinding.ActivityMainBinding
import me.kariot.wallsapp.databinding.LayoutErrorBinding
import me.kariot.wallsapp.model.ModelWallpaperResponse
import me.kariot.wallsapp.presenter.WallpaperPresenter
import me.kariot.wallsapp.presenter.WallpaperView

const val TAG = "WallsApp"

class MainActivity : AppCompatActivity(), WallpaperView {

    lateinit var presenter: WallpaperPresenter
    lateinit var binding: ActivityMainBinding
    lateinit var errorBinding: LayoutErrorBinding
    lateinit var adapter: ImagesAdapter

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
        adapter = ImagesAdapter()
        binding.viewPager2.adapter = adapter
        errorBinding.btnRetry.setOnClickListener {
            presenter.loadPictures()
        }
    }

    override fun isLoading() {
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun onWallpaperLoaded(wallapapers: List<ModelWallpaperResponse.Photo?>?) {
        binding.swipeRefreshLayout.isRefreshing = false
        errorBinding.layError.isVisible = false
        wallapapers?.let {
            adapter.addData(it)
        }
    }

    override fun error(message: String, isFirstRequest: Boolean) {
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