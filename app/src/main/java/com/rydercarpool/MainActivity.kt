// ...existing code...
package com.rydercarpool

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.activities.LiveLocationActivity
import com.rydercarpool.activities.RatingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // primary buttons (nullable — tolerate missing IDs)
        val loginButton = findViewById<Button?>(R.id.loginButton)
        val signupButton = findViewById<Button?>(R.id.signupButton)
        val safetyButton = findViewById<Button?>(R.id.safetyButton)

        // alternative / consolidated IDs for additional features
        val startRideButton = findViewById<Button?>(R.id.btnStartRide) ?: findViewById(R.id.start_ride_button)
        val liveLocationButton = findViewById<Button?>(R.id.btnLiveLocation) ?: findViewById(R.id.live_location_button)
        val ratingsButton = findViewById<Button?>(R.id.btnRatings) ?: findViewById(R.id.ratings_button)

        loginButton?.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        signupButton?.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }

        safetyButton?.setOnClickListener {
            startActivity(Intent(this, LiveLocationActivity::class.java))
        }

        startRideButton?.setOnClickListener {
            Toast.makeText(this, "Start Ride clicked", Toast.LENGTH_SHORT).show()
        }

        liveLocationButton?.setOnClickListener {
            startActivity(Intent(this, LiveLocationActivity::class.java))
        }

        ratingsButton?.setOnClickListener {
            startActivity(Intent(this, RatingActivity::class.java))
        }

        if (loginButton == null && signupButton == null && safetyButton == null
            && startRideButton == null && liveLocationButton == null && ratingsButton == null) {
            Toast.makeText(this, "No actionable views found in layout", Toast.LENGTH_LONG).show()
        }
    }
}