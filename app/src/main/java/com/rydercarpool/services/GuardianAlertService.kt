package com.rydercarpool.services

import android.content.Context
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast

class GuardianAlertService(private val context: Context) {

    fun sendAlert(userId: String, message: String) {
        // Actually use the userId parameter
        Log.d("GuardianAlert", "Sending alert for user: $userId - $message")
        
        val guardianNumber = getGuardianNumber(userId)
        if (guardianNumber.isNotEmpty()) {
            sendSMS(guardianNumber, message)
            Log.i("GuardianAlert", "Alert sent successfully for user: $userId")
        } else {
            Toast.makeText(context, "No guardian number found for user: $userId", Toast.LENGTH_SHORT).show()
            Log.w("GuardianAlert", "No guardian number found for user: $userId")
        }
    }

    private fun getGuardianNumber(userId: String): String {
        // In real implementation, fetch from database or SharedPreferences using userId
        // For now, return a dummy number
        return "1234567890"
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        try {
            val smsManager = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                context.getSystemService(SmsManager::class.java)
            } else {
                @Suppress("DEPRECATION")
                SmsManager.getDefault()
            }
            
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(context, "Alert sent to guardian", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("GuardianAlert", "Failed to send SMS: ${e.message}")
            Toast.makeText(context, "Failed to send alert", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendEmergencyAlert(userId: String, location: String) {
        val message = "EMERGENCY! User $userId needs help. Location: $location"
        sendAlert(userId, message)
    }

    fun updateGuardianNumber(userId: String, newNumber: String) {
        // In real implementation, save to database or SharedPreferences
        Log.d("GuardianAlert", "Updated guardian number for user: $userId to: $newNumber")
    }
}