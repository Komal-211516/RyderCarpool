package com.rydercarpool.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.telephony.SmsManager
import android.widget.Toast

class GuardianAlertService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val guardianPhone = intent?.getStringExtra("guardian_phone")
        val passengerName = intent?.getStringExtra("passenger_name") ?: "User"

        if (guardianPhone != null) {
            sendSMSAlert(guardianPhone, passengerName)
        }

        stopSelf()
        return START_NOT_STICKY
    }

    private fun sendSMSAlert(guardianPhone: String, passengerName: String) {
        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            val message = "ALERT: $passengerName's ride is delayed beyond estimated time. Please check on them."

            smsManager.sendTextMessage(guardianPhone, null, message, null, null)

            Toast.makeText(this, "Guardian alert sent!", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to send alert: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null
}