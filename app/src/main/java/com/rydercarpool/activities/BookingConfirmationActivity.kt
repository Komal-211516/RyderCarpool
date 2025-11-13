package com.rydercarpool.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R

class BookingConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_confirmation)

        // Get data from intent
        val bookingId = intent.getStringExtra("booking_id") ?: "N/A"
        val bookingTime = intent.getStringExtra("booking_time") ?: "N/A"
        val fromLocation = intent.getStringExtra("from_location") ?: "N/A"
        val toLocation = intent.getStringExtra("to_location") ?: "N/A"
        val date = intent.getStringExtra("date") ?: "N/A"
        val time = intent.getStringExtra("time") ?: "N/A"
        val price = intent.getDoubleExtra("price", 0.0)

        displayConfirmation(bookingId, bookingTime, fromLocation, toLocation, date, time, price)
        setupButtonListeners()
    }

    private fun displayConfirmation(
        bookingId: String,
        bookingTime: String,
        fromLocation: String,
        toLocation: String,
        date: String,
        time: String,
        price: Double
    ) {
        findViewById<TextView>(R.id.tvBookingId).text = "Booking ID: $bookingId"
        findViewById<TextView>(R.id.tvBookingTime).text = "Booked at: $bookingTime"
        findViewById<TextView>(R.id.tvRoute).text = "$fromLocation to $toLocation"
        findViewById<TextView>(R.id.tvDate).text = "Date: $date"
        findViewById<TextView>(R.id.tvTime).text = "Time: $time"
        findViewById<TextView>(R.id.tvPrice).text = "Price: $$price"
    }

    private fun setupButtonListeners() {
        // Back to Home button
        findViewById<Button>(R.id.btnBackToHome).setOnClickListener {
            finish()
        }

        // View Rides button
        findViewById<Button>(R.id.btnViewRides).setOnClickListener {
            val intent = Intent(this, FindRideActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}