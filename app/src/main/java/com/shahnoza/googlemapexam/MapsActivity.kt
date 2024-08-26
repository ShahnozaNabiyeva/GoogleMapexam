package com.shahnoza.googlemapexam

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shahnoza.googlemapexam.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.mapType=GoogleMap.MAP_TYPE_HYBRID // harita ko'rinishini o'zgartirish


       getDeviceLocation()

    }

    // bir marta joylashuvni olish dastur ishlaganda
    @SuppressLint("MissingPermission")
    private fun getDeviceLocation(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        try {

            val locationResult=fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener(this@MapsActivity){
                if (it.isSuccessful){
                    val location=it.result
                    val cameraPosition=CameraPosition.Builder().target(LatLng(location.latitude,location.longitude)).tilt(60F)
                        .zoom(17f).bearing(0f).build()


                    // Add a marker in Sydney and move the camera

                    mMap.addMarker(MarkerOptions().position(LatLng(location.latitude,location.longitude)).title("Marker joylashuv"))
                   // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude),17.0F))
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))


                }
            }
        }catch (e:Exception){
            Log.d(TAG,"getDevice: ${e.message}")
        }


    }

}