package com.example.workoutapp.ui

import android.content.Context
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.concurrent.TimeUnit

class LocationUpdatesLiveData(context: Context) : LiveData<String>() {

    private val locationClient: FusedLocationProviderClient
        = LocationServices.getFusedLocationProviderClient(context)

//    private val geocoder by lazy {
//        Geocoder(context, Locale.ENGLISH)
//    }

    private val job = Job()

    private val scope = CoroutineScope(job + Dispatchers.IO)

    private val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        TimeUnit.SECONDS.toMillis(5)
    ).build()

    private val locationCallback = object: LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            p0.lastLocation?.let {
                postValue("${it.latitude},${it.longitude}")
            }
        }
    }
    override fun onActive() {
        super.onActive()
        try {
            locationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        }
        catch (e: SecurityException){
            Log.d("LocationUpdatesLiveData", "Missing location permission")
        }

    }

    override fun onInactive() {
        super.onInactive()
        job.cancel()
        locationClient.removeLocationUpdates(locationCallback)
    }
}