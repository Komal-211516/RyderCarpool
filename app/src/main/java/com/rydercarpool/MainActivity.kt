package com.rydercarpool

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.rydercarpool.activities.BookRideActivity
import com.rydercarpool.activities.LiveLocationActivity
import com.rydercarpool.activities.RatingActivity
import com.rydercarpool.activities.LoginActivity
import com.rydercarpool.activities.SignupActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Check if user is logged in
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            // Redirect to login if not authenticated
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        setContentView(R.layout.activity_main)

        // Initialize all buttons with proper null safety
        val btnBookRide = findViewById<Button?>(R.id.btnBookRide)
        val btnLiveLocation = findViewById<Button?>(R.id.btnLiveLocation)
        val btnRatings = findViewById<Button?>(R.id.btnRatings)
        
        // Alternative button IDs (if you have them in your layout)
        val startRideButton = findViewById<Button?>(R.id.btnStartRide) ?: findViewById<Button?>(R.id.start_ride_button)
        val loginButton = findViewById<Button?>(R.id.loginButton)
        val signupButton = findViewById<Button?>(R.id.signupButton)
        val safetyButton = findViewById<Button?>(R.id.safetyButton)

        // Set up click listeners for main navigation buttons
        btnBookRide?.setOnClickListener {
            val intent = Intent(this, BookRideActivity::class.java)
            startActivity(intent)
        }

        btnLiveLocation?.setOnClickListener {
            val intent = Intent(this, LiveLocationActivity::class.java)
            startActivity(intent)
        }

        btnRatings?.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        // Alternative button handlers
        startRideButton?.setOnClickListener {
            // If you want this to also navigate to BookRideActivity
            val intent = Intent(this, BookRideActivity::class.java)
            startActivity(intent)
        }

        loginButton?.setOnClickListener {
            // Since user is already logged in, maybe show profile or logout
            Toast.makeText(this, "Already logged in", Toast.LENGTH_SHORT).show()
        }

        signupButton?.setOnClickListener {
            // Since user is already logged in, maybe show profile
            Toast.makeText(this, "Already registered", Toast.LENGTH_SHORT).show()
        }

        safetyButton?.setOnClickListener {
            val intent = Intent(this, LiveLocationActivity::class.java)
            startActivity(intent)
        }

        // Optional: Check if no buttons were found (for debugging)
        if (btnBookRide == null && btnLiveLocation == null && btnRatings == null &&
            startRideButton == null && loginButton == null && signupButton == null && safetyButton == null) {
            Toast.makeText(this, "No actionable views found in layout", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()
        // Optional: Additional setup when activity becomes visible
        Toast.makeText(this, "Welcome to Ryder Carpool!", Toast.LENGTH_SHORT).show()
    }
}