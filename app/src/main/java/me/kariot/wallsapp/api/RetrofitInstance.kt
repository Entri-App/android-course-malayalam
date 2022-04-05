package me.kariot.wallsapp.api

import me.kariot.wallsapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", Constants.API_KEY).build()
            chain.proceed(request)
        }.build()

        Retrofit.Builder().baseUrl(Constants.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val wallpaperAPI by lazy {
        retrofit.create(WallpaperAPI::class.java)
    }

}