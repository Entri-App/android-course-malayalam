package me.kariot.wallsapp.api

import me.kariot.wallsapp.model.ModelWallpaperResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperAPI {

    @GET("curated")
    fun getWallpapers(
        @Query("page")
        page: Int,
        @Query("per_page")
        perPage: Int

    ): Call<ModelWallpaperResponse>

}