package me.kariot.weatherapp.api

import me.kariot.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("current.json")
    fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): Call<WeatherData>
}