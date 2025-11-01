package com.rydercarpool.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class LiveLocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_location)

        initializeViews()
    }

    private fun initializeViews() {
        val tvLocationStatus: TextView = findViewById(R.id.tvLocationStatus)
        val btnShareLocation: Button = findViewById(R.id.btnShareLocation)
        val tvDriver: TextView = findViewById(R.id.tvDriver)
        val tvPickup: TextView = findViewById(R.id.tvPickup)
        val tvDestination: TextView = findViewById(R.id.tvDestination)
        val tvFare: TextView = findViewById(R.id.tvFare)
        val btnBook: Button = findViewById(R.id.btnBook)

        tvDriver.text = "Driver: John Doe"
        tvPickup.text = "From: New York"
        tvDestination.text = "To: Boston"
        tvFare.text = "Fare: $45.00"
        tvLocationStatus.text = "Location: Ready to share"

        btnShareLocation.setOnClickListener {
            Toast.makeText(this, "Sharing location...", Toast.LENGTH_SHORT).show()
            tvLocationStatus.text = "Location: Sharing active"
            simulateLocationUpdate(tvLocationStatus)
        }

        btnBook.setOnClickListener {
            Toast.makeText(this, "Ride booked successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun simulateLocationUpdate(locationView: TextView) {
        val lat = 40.7128 + (Math.random() - 0.5) * 0.01
        val lng = -74.0060 + (Math.random() - 0.5) * 0.01
        locationView.text = "Location: Lat ${"%.4f".format(lat)}, Lng ${"%.4f".format(lng)}"
    }
}