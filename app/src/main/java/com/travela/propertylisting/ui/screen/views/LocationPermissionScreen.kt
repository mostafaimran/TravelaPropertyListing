package com.travela.propertylisting.ui.screen.views

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.travela.propertylisting.datamodel.ext.getCurrentLocation
import com.travela.propertylisting.datamodel.ext.hasLocationPermission
import com.travela.propertylisting.datamodel.models.Location


@Composable
fun LocationPermissionScreen(onLocationFound: (Location) -> Unit, permissionDenied: () -> Unit) {
    val context = LocalContext.current

    var requestLocationPermission by remember { mutableStateOf(false) }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { permissions ->
                val permissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
                    acc && isPermissionGranted
                }

                if (permissionsGranted) {
                    // Permission granted, update the location
                    context.getCurrentLocation { lat, long ->
                        onLocationFound(Location(lat, long))
                    }
                } else {
                    permissionDenied()
                }
            }
        )

    /*LaunchedEffect(requestLocationPermission) {
        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }*/

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START && requestLocationPermission) {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                    )
                )
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    if (context.hasLocationPermission()) {
        // Permission already granted, update the location
        context.getCurrentLocation { lat, long ->
            onLocationFound(Location(lat, long))
        }
    } else {
        // Request location permission

        requestLocationPermission = true
    }
}
