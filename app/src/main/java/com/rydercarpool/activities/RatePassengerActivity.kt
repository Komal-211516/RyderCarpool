package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class RatePassengerActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var etComment: EditText
    private lateinit var btnSubmitRating: Button
    private lateinit var btnSkip: Button

    private var passengerName: String = ""
    private var rideId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_passenger)

        initializeViews()
        getIntentData()
        setupClickListeners()
    }

    private fun initializeViews() {
        ratingBar = findViewById(R.id.ratingBar)
        etComment = findViewById(R.id.etComment)
        btnSubmitRating = findViewById(R.id.btnSubmitRating)
        btnSkip = findViewById(R.id.btnSkip)
    }

    private fun getIntentData() {
        passengerName = intent.getStringExtra("passenger_name") ?: "Passenger"
        rideId = intent.getStringExtra("ride_id") ?: ""

        // Set passenger name in UI
        findViewById<TextView>(R.id.tvUserName).text = passengerName
        findViewById<TextView>(R.id.tvUserType).text = "Passenger"
    }

    private fun setupClickListeners() {
        btnSubmitRating.setOnClickListener {
            submitRating()
        }

        btnSkip.setOnClickListener {
            skipRating()
        }

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            updateRatingDescription(rating)
        }
    }

    private fun updateRatingDescription(rating: Float) {
        val description = when (rating) {
            1f -> "Poor"
            2f -> "Fair"
            3f -> "Good"
            4f -> "Very Good"
            5f -> "Excellent"
            else -> "How was the passenger?"
        }
        findViewById<TextView>(R.id.tvRatingDescription).text = description
    }

    private fun submitRating() {
        val rating = ratingBar.rating
        val comment = etComment.text.toString().trim()

        if (rating == 0f) {
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show()
            return
        }

        // Here you would typically save the rating to your backend
        Toast.makeText(this, "Rating submitted: $rating stars", Toast.LENGTH_SHORT).show()

        // Navigate back or to next screen
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun skipRating() {
        Toast.makeText(this, "Rating skipped", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
        finish()
    }
}