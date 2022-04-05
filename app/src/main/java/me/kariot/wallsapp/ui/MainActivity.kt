package me.kariot.wallsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.kariot.wallsapp.R
import me.kariot.wallsapp.model.ModelWallpaperResponse
import me.kariot.wallsapp.presenter.WallpaperPresenter
import me.kariot.wallsapp.presenter.WallpaperView

class MainActivity : AppCompatActivity(), WallpaperView {

    lateinit var presenter: WallpaperPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = WallpaperPresenter(this)
        presenter.loadPictures()
    }

    override fun isLoading() {
        TODO("Not yet implemented")
    }

    override fun onWallpaperLoaded(wallapapers: List<ModelWallpaperResponse.Photo?>?) {
        TODO("Not yet implemented")
    }

    override fun error(message: String, isFirstRequest: Boolean) {
        TODO("Not yet implemented")
    }
}