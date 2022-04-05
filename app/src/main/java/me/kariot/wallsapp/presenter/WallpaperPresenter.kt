package me.kariot.wallsapp.presenter

import me.kariot.wallsapp.api.RetrofitInstance
import me.kariot.wallsapp.model.ModelWallpaperResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WallpaperPresenter(private var callback: WallpaperView) {

    private var page = 1
    private var per_page = 15

    fun loadPictures() {
        callback.isLoading()
        RetrofitInstance.wallpaperAPI.getWallpapers(page, per_page)
            .enqueue(object : Callback<ModelWallpaperResponse> {
                override fun onResponse(
                    call: Call<ModelWallpaperResponse>,
                    response: Response<ModelWallpaperResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onWallpaperLoaded(response.body()?.photos)
                    } else {
                        callback.error("API error", page == 1)
                    }
                }

                override fun onFailure(call: Call<ModelWallpaperResponse>, t: Throwable) {
                    callback.error("An network error occured", page == 1)
                }
            })
    }

}

interface WallpaperView {
    fun isLoading()
    fun onWallpaperLoaded(wallapapers: List<ModelWallpaperResponse.Photo?>?)
    fun error(message: String, isFirstRequest: Boolean)
}
