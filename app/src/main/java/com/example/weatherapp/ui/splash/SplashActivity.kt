package com.example.weatherapp.ui.splash

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivitySplashBinding
import com.example.weatherapp.ui.main.MainActivity
import com.example.weatherapp.utils.ActivityExt.Companion.toActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(R.layout.activity_splash), EasyPermissions.PermissionCallbacks {

    private val binding: ActivitySplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        methodRequiresTwoPermission()
    }

    private fun navigateToMain(){
        lifecycleScope.launch {
            delay(2000)
            this@SplashActivity.toActivity<MainActivity>()
        }
    }

    @AfterPermissionGranted(RC_LOCATION)
    private fun methodRequiresTwoPermission() {
        val perms = ACCESS_FINE_LOCATION
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            navigateToMain()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this, "",
                RC_LOCATION, perms
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        navigateToMain()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    companion object{
        const val RC_LOCATION = 1
    }
}