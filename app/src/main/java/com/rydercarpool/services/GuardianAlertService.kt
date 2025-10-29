// ...existing code...
package com.rydercarpool.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
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

    private val CHANNEL_ID = "guardian_channel"
    private val NOTIFICATION_ID = 1

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        sharedPreferences = getSharedPreferences("RyderCarpool", MODE_PRIVATE)
        smsManager = SmsManager.getDefault()

        createNotificationChannel()
        createLocationCallback()
        startForegroundServiceWithNotification()
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
            interval = 30_000L
            fastestInterval = 15_000L
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (e: SecurityException) {
            Toast.makeText(this, "Location permission required", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            println("Failed to request location updates: ${e.message}")
        }
    }

    private fun sendLocationToGuardian(location: Location) {
        val guardianNumber = sharedPreferences.getString("guardian_number", "") ?: ""

        if (guardianNumber.isNotEmpty()) {
            val googleMapsLink = "https://maps.google.com/?q=${location.latitude},${location.longitude}"
            val message =
                "Ryder Carpool Safety Alert - I'm currently at: $googleMapsLink - Lat: ${location.latitude}, Long: ${location.longitude}"

            try {
                smsManager.sendTextMessage(guardianNumber, null, message, null, null)
                println("Guardian alert sent: $message")
            } catch (e: SecurityException) {
                Toast.makeText(this, "SMS permission required", Toast.LENGTH_SHORT).show()
                println("Failed to send SMS (permission): ${e.message}")
            } catch (e: Exception) {
                println("Failed to send SMS: ${e.message}")
            }
        } else {
            println("No guardian number configured.")
        }
    }

    private fun startForegroundServiceWithNotification() {
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Ryder Carpool Safety")
            .setContentText("Live location sharing active")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()

        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Guardian Alerts"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = "Channel for guardian live location sharing"
            }
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        } catch (e: Exception) {
            println("Error removing location updates: ${e.message}")
        }
    }
}