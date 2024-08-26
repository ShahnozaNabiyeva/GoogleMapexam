package com.shahnoza.googlemapexam

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.shahnoza.googlemapexam.databinding.ActivityMainBinding

const val TAG="MainActivity"
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fusedLocationProviderClient:FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


     getDeviceLocation()


    }
    // bir marta joylashuvni olish dastur ishlaganda
    @SuppressLint("MissingPermission")
    private fun getDeviceLocation(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        try {

            val locationResult=fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener(this@MainActivity){
                if (it.isSuccessful){
                    val location=it.result
                    //binding.tv.text="Lat: ${location.latitude}\nLong: ${location.longitude}"
                    Log.d(TAG,"getDevice: lat: ${location.latitude}")
                    Log.d(TAG,"getDeviceLocation: Long: ${location.longitude}")
                   // "https://www.google.com/maps/@40.3829751,71.7801353,17z?authuser=0&entry=ttu"


                }
            }
        }catch (e:Exception){
            Log.d(TAG,"getDevice: ${e.message}")
        }


    }
}