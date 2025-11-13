package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class RideCompletionActivity : AppCompatActivity() {

    private lateinit var btnGoHome: Button
    private lateinit var btnViewHistory: Button
    private lateinit var btnBookAnother: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_completion)

        initializeViews()
        setupClickListeners()
        setCompletionDetails()
    }

    private fun initializeViews() {
        btnGoHome = findViewById(R.id.btnGoHome)
        btnViewHistory = findViewById(R.id.btnViewHistory)
        btnBookAnother = findViewById(R.id.btnBookAnother)
    }

    private fun setupClickListeners() {
        btnGoHome.setOnClickListener {
            goToHome()
        }

        btnViewHistory.setOnClickListener {
            viewRideHistory()
        }

        btnBookAnother.setOnClickListener {
            bookAnotherRide()
        }
    }

    private fun setCompletionDetails() {
        // Set ride completion details if needed
        val distance = intent.getDoubleExtra("distance", 0.0)
        val duration = intent.getStringExtra("duration") ?: "0 min"
        val price = intent.getDoubleExtra("price", 0.0)

        val details = "Distance: ${"%.1f".format(distance)} km • Time: $duration • Paid: ₹$price"
        findViewById<TextView>(R.id.tvCompletionDetails).text = details
    }

    private fun goToHome() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun viewRideHistory() {
        // For now, go to dashboard. You can create RideHistoryActivity later
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun bookAnotherRide() {
        val intent = Intent(this, FindRideActivity::class.java)
        startActivity(intent)
        finish()
    }
}