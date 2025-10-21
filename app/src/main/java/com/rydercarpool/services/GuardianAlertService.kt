package com.rydercarpool.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class GuardianAlertService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Guardian alert logic will go here
        return START_STICKY
    }
}
