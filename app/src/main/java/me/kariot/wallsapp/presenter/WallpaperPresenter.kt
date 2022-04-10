package me.kariot.wallsapp.presenter

import me.kariot.wallsapp.api.RetrofitInstance
import me.kariot.wallsapp.model.ModelWallpaperResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WallpaperPresenter(private var callback: WallpaperView) {

    private var page = 1
    private var per_page = 15

    var wallpapers = ArrayList<ModelWallpaperResponse.Photo?>()

    fun loadPictures() {
        callback.isLoading()
        RetrofitInstance.wallpaperAPI.getWallpapers(page, per_page)
            .enqueue(object : Callback<ModelWallpaperResponse> {
                override fun onResponse(
                    call: Call<ModelWallpaperResponse>,
                    response: Response<ModelWallpaperResponse>
                ) {
                    if (response.isSuccessful) {
                        callback.onWallpaperLoaded(
                            response.body()?.photos,
                            response.body()?.total_results ?: per_page
                        )
                        response.body()?.photos?.let {
                            wallpapers.addAll(it)
                        }
                    } else {
                        callback.error("API error", page == 1)
                    }
                }

                override fun onFailure(call: Call<ModelWallpaperResponse>, t: Throwable) {
                    callback.error("An network error occurred", page == 1)
                }
            })
    }

    fun getNextPage() {
        page++
        loadPictures()
    }

    fun clearData() {
        wallpapers.clear()
        page = 1
    }


}

interface WallpaperView {
    fun isLoading()
    fun onWallpaperLoaded(wallapapers: List<ModelWallpaperResponse.Photo?>?, totalPage: Int)
    fun error(message: String, isFirstRequest: Boolean)
}
