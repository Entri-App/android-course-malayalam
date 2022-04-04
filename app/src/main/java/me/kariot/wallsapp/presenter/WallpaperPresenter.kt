package me.kariot.wallsapp.presenter

class WallpaperPresenter(private var callback: WallpaperView) {

    fun loadPictures() {
        callback.isLoading()
        //TODO:- API Call
    }

}

interface WallpaperView {
    fun isLoading()
    fun onWallpaperLoaded()
    fun error(message: String, isFirstRequest: Boolean)
}
