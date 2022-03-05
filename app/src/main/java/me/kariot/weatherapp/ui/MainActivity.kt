package me.kariot.weatherapp.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.kariot.weatherapp.api.RetrofitInstance
import me.kariot.weatherapp.databinding.ActivityMainBinding
import me.kariot.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "WeatherApp"

class MainActivity : AppCompatActivity() {
    private val apiKey = "26e593e5f57a4b9b93f182154222702"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {
        binding.btnFindWeather.setOnClickListener {
            val city = binding.edtCity.text.toString()
            if (city.isBlank()) {
                return@setOnClickListener
            }
            sendRequest(city)
        }
    }

    private fun sendRequest(city: String) {

        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Finding Weather....")
        pDialog.show()

        val apiCall = RetrofitInstance.weatherAPI.getWeatherData(apiKey, city)
        apiCall.enqueue(object : Callback<WeatherData> {
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (response.isSuccessful) {
                    val name = response.body()?.location?.name
                    val region = response.body()?.location?.region
                    val temp_c = response.body()?.current?.temp_c

                    binding.txtWeatherInfo.text = "$name, $region\ntemperature is $temp_c"
                }
                pDialog.dismiss()
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                t.printStackTrace()
                pDialog.dismiss()
            }

        })

//        val url =
//            "?key=&q=$city&aqi=no"

    }
}