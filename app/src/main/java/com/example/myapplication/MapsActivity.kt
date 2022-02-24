package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.eazypermissions.common.model.PermissionResult
import com.eazypermissions.dsl.extension.requestPermissions
import com.example.myapplication.databinding.ActivityMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.findLocationButton.setOnClickListener {
            checkLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun checkLocationPermission() {

        hasLocationPermission {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    Log.d(TAG, "checkLocationPermission: lat ${location?.latitude}")
                    Log.d(TAG, "checkLocationPermission: lng ${location?.longitude}")

                    if (location != null) {
                        val geocoder = Geocoder(this)
                        val currentLocation =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        if (currentLocation.isNotEmpty()) {
                            Log.d(
                                TAG,
                                "checkLocationPermission: loc : ${currentLocation.first()?.getAddressLine(0)}"
                            )
                            Log.d(
                                TAG,
                                "checkLocationPermission: loc : ${currentLocation.first()?.countryName}"
                            )
                        }
                    }
                }
        }


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val kochi = LatLng(9.9312, 76.2673)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(kochi).title("Marker in Kochi"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kochi, 18f))
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        mMap.isMyLocationEnabled = true
        mMap.setOnMapClickListener { latLng ->
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(latLng).title("New Marker"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }

    }

    private fun hasLocationPermission(hasPermission: () -> Unit) {
        requestPermissions(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) {
            requestCode = 4
            resultCallback = {
                when (this) {
                    is PermissionResult.PermissionGranted -> {
                        hasPermission()
                    }
                    is PermissionResult.PermissionDenied -> {
                        Toast.makeText(this@MapsActivity, "Permission Denied", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is PermissionResult.PermissionDeniedPermanently -> {
                        Toast.makeText(
                            this@MapsActivity,
                            "Permission Denied Permanently",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is PermissionResult.ShowRational -> {
                        Toast.makeText(
                            this@MapsActivity,
                            "Permission Denied Show Rational",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}