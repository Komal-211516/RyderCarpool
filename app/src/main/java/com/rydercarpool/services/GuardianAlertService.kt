package com.rydercarpool.services

import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.os.IBinder
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.rydercarpool.R

class GuardianAlertService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var smsManager: SmsManager

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        sharedPreferences = getSharedPreferences("RyderCarpool", MODE_PRIVATE)
        smsManager = SmsManager.getDefault()
        
        createLocationCallback()
        startForegroundService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startLocationTracking()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    sendLocationToGuardian(location)
                }
            }
        }
    }

    private fun startLocationTracking() {
        val locationRequest = LocationRequest.create().apply {
            interval = 30000
            fastestInterval = 15000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        } catch (e: SecurityException) {
            Toast.makeText(this, "Location permission required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendLocationToGuardian(location: Location) {
        val guardianNumber = sharedPreferences.getString("guardian_number", "") ?: ""
        
        if (guardianNumber.isNotEmpty()) {
            val googleMapsLink = "https://maps.google.com/?q=${location.latitude},${location.longitude}"
            val message = "Ryder Carpool Safety Alert - I'm currently at: $googleMapsLink - Lat: ${location.latitude}, Long: ${location.longitude}"

            try {
                smsManager.sendTextMessage(guardianNumber, null, message, null, null)
                println("Guardian alert sent: $message")
            } catch (e: Exception) {
                println("Failed to send SMS: ${e.message}")
            }
        }
    } // Added missing closing brace

    private fun startForegroundService() {
        val notification = NotificationCompat.Builder(this, "guardian_channel")
            .setContentTitle("Ryder Carpool Safety")
            .setContentText("Live location sharing active")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        startForeground(1, notification)
    } // Added missing closing brace

    // Remove these duplicate/placeholder functions or implement them properly:
    // fun startLocationTracking() { } // DUPLICATE - REMOVE
    // fun stopLocationTracking() { } // PLACEHOLDER - REMOVE OR IMPLEMENT
    // fun sendAlertToGuardian() { } // PLACEHOLDER - REMOVE OR IMPLEMENT

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}