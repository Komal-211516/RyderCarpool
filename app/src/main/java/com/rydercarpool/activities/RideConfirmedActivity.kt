package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class RideConfirmedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_confirmed)

        val btnViewRide = findViewById<Button>(R.id.btnViewRide)
        val btnGoHome = findViewById<Button>(R.id.btnGoHome)

        btnViewRide.setOnClickListener {
            // Navigate to ride tracking
            val intent = Intent(this, RideTrackingActivity::class.java)
            startActivity(intent)
        }

        btnGoHome.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}