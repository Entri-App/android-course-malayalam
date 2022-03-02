package me.kariot.weatherapp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import me.kariot.weatherapp.databinding.ActivityMainBinding

const val TAG = "WeatherApp"

class MainActivity : AppCompatActivity() {

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

        val url =
            "https://api.weatherapi.com/v1/current.json?key=26e593e5f57a4b9b93f182154222702&q=$city&aqi=no"
        val requestObj =
            JsonObjectRequest(Request.Method.GET, url, null, { response ->

                val locationObj = response.getJSONObject("location")
                val region = locationObj.getString("region")
                val country = locationObj.getString("country")

                val currentObj = response.getJSONObject("current")
                val temp_c = currentObj.getDouble("temp_c")

                binding.txtWeatherInfo.text =
                    "Region : $region\nCountry : $country\nTemperature : $temp_c"
                pDialog.dismiss()
            }, { error ->
                Log.d(TAG, "Error : ${error.localizedMessage}")
                pDialog.dismiss()
            })
        MySingleton.getInstance(this).addToRequestQueue(requestObj)
    }
}