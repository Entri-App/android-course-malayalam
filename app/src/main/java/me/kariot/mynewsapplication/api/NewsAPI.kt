package me.kariot.mynewsapplication.api

import me.kariot.mynewsapplication.model.newsModel.NewsResponse
import me.kariot.mynewsapplication.utils.ApiEndPoints
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET(ApiEndPoints.TOP_HEADLINES)
    fun getLatestNews(
        @Query("country")
        country: String,
        @Query("apiKey")
        key: String,
        @Query("pageSize")
        pageSize: Int,
        @Query("page")
        page: Int
    ): Call<NewsResponse>
}