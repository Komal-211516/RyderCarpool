package com.rydercarpool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.rydercarpool.activities.LiveLocationActivity
import com.rydercarpool.activities.RatingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Try different possible button IDs
        val btnStartRide = findViewById<Button>(R.id.btnStartRide)
        val btnLiveLocation = findViewById<Button>(R.id.btnLiveLocation)
        val btnRatings = findViewById<Button>(R.id.btnRatings)

        // If the above IDs don't exist, try these common ones
        val startRideButton = findViewById<Button>(R.id.start_ride_button)
        val liveLocationButton = findViewById<Button>(R.id.live_location_button)
        val ratingsButton = findViewById<Button>(R.id.ratings_button)

        // Set up click listeners for whichever buttons exist
        btnStartRide?.setOnClickListener {
            Toast.makeText(this, "Start Ride clicked", Toast.LENGTH_SHORT).show()
        }

        startRideButton?.setOnClickListener {
            Toast.makeText(this, "Start Ride clicked", Toast.LENGTH_SHORT).show()
        }

        btnLiveLocation?.setOnClickListener {
            val intent = Intent(this, LiveLocationActivity::class.java)
            startActivity(intent)
        }

        liveLocationButton?.setOnClickListener {
            val intent = Intent(this, LiveLocationActivity::class.java)
            startActivity(intent)
        }

        btnRatings?.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        ratingsButton?.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        // If no buttons found, show a message
        if (btnStartRide == null && startRideButton == null) {
            Toast.makeText(this, "No buttons found in layout", Toast.LENGTH_LONG).show()
        }
    }
}
