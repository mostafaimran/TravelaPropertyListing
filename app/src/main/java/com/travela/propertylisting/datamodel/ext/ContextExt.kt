package com.travela.propertylisting.datamodel.ext

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices


fun Context.hasLocationPermission(): Boolean {
    return ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("MissingPermission")
fun Context.getCurrentLocation(callback: (Double, Double) -> Unit) {
    if (!this.hasLocationPermission()) {
        return
    }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location ->
            if (location != null) {
                val lat = location.latitude
                val long = location.longitude
                callback(lat, long)
            }
        }
        .addOnFailureListener { exception ->
            // Handle location retrieval failure
            exception.printStackTrace()
        }
}