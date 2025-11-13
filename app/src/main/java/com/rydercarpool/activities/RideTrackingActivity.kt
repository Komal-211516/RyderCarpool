package com.rydercarpool.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class RideTrackingActivity : AppCompatActivity() {

    private lateinit var tvStatus: TextView
    private lateinit var btnBack: Button
    private lateinit var tvRideInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_tracking)

        initializeViews()
        setupClickListeners()
        loadRideData()
    }

    private fun initializeViews() {
        tvStatus = findViewById(R.id.tvStatus)
        btnBack = findViewById(R.id.btnBack)
        tvRideInfo = findViewById(R.id.tvRideInfo)
    }

    private fun setupClickListeners() {
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun loadRideData() {
        // Get ride data from intent
        val rideId = intent.getStringExtra("rideId")
        val driverName = intent.getStringExtra("driverName")
        val pickupLocation = intent.getStringExtra("pickupLocation")
        val destination = intent.getStringExtra("destination")

        // Update UI with ride information
        tvStatus.text = "Ride in Progress"

        val rideInfo = """
            Driver: $driverName
            From: $pickupLocation
            To: $destination
            Status: On the way
        """.trimIndent()

        tvRideInfo.text = rideInfo

        // Here you would typically set up real-time tracking
        // and update the status accordingly
    }
}