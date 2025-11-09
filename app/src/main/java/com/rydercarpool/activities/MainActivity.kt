package com.rydercarpool.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("RyderCarpool", MODE_PRIVATE)
        initializeViews()
        checkUserAuthentication()
    }

    private fun initializeViews() {
        val btnBookRide: Button = findViewById(R.id.btnBookRide)
        val btnRideHistory: Button = findViewById(R.id.btnRideHistory)
        val btnProfile: Button = findViewById(R.id.btnProfile)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnSignup: Button = findViewById(R.id.btnSignup)

        btnBookRide.setOnClickListener {
            startActivity(Intent(this, BookRideActivity::class.java))
        }

        btnRideHistory.setOnClickListener {
            startActivity(Intent(this, RideHistoryActivity::class.java))
        }

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun checkUserAuthentication() {
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            // User is logged in, enable all features
            enableMainFeatures()
        } else {
            // User is not logged in, show authentication prominently
            showAuthenticationOptions()
        }
    }

    private fun enableMainFeatures() {
        findViewById<Button>(R.id.btnBookRide).isEnabled = true
        findViewById<Button>(R.id.btnRideHistory).isEnabled = true
        findViewById<Button>(R.id.btnProfile).isEnabled = true
        findViewById<Button>(R.id.btnLogin).visibility = Button.GONE
        findViewById<Button>(R.id.btnSignup).visibility = Button.GONE
    }

    private fun showAuthenticationOptions() {
        findViewById<Button>(R.id.btnBookRide).isEnabled = false
        findViewById<Button>(R.id.btnRideHistory).isEnabled = false
        findViewById<Button>(R.id.btnProfile).isEnabled = false
        findViewById<Button>(R.id.btnLogin).visibility = Button.VISIBLE
        findViewById<Button>(R.id.btnSignup).visibility = Button.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        // Check authentication state when returning to this activity
        checkUserAuthentication()
    }
}