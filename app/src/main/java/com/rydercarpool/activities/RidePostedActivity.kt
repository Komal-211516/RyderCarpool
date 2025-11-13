package com.rydercarpool.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rydercarpool.databinding.ActivityRidePostedBinding

class RidePostedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRidePostedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRidePostedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startLocation = intent.getStringExtra("START_LOCATION") ?: "Unknown Location"
        val endLocation = intent.getStringExtra("END_LOCATION") ?: "Unknown Destination"
        val distance = intent.getDoubleExtra("DISTANCE", 0.0)
        val seats = intent.getIntExtra("SEATS", 1)
        val fare = intent.getIntExtra("FARE", 0)
        val carModel = intent.getStringExtra("CAR_MODEL") ?: "Unknown Car"
        val carNumber = intent.getStringExtra("CAR_NUMBER") ?: "Unknown Number"
        val departureTime = intent.getStringExtra("DEPARTURE_TIME") ?: "Unknown Time"

        displayRideConfirmation(startLocation, endLocation, distance, seats, fare, carModel, carNumber, departureTime)

        binding.btnBackToDashboard.setOnClickListener {
            goToDashboard()
        }

        binding.btnViewMyOffers.setOnClickListener {
            Toast.makeText(this, "My Offers feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayRideConfirmation(startLocation: String, endLocation: String, distance: Double,
                                        seats: Int, fare: Int, carModel: String, carNumber: String, departureTime: String) {
        binding.tvRideSummary.text = "📍 From: $startLocation\n📍 To: $endLocation\n📏 Distance: ${"%.1f".format(distance)} km\n💺 Available Seats: $seats\n⏰ Departure: $departureTime\n🚗 Car: $carModel ($carNumber)"
        binding.tvEarnings.text = "Potential Earnings: ₹$fare"

        val offerId = System.currentTimeMillis().toString().takeLast(6)
        binding.tvAdditionalInfo.text = "Offer ID: DRIVER$offerId\nWaiting for passengers to book your ride"
    }

    private fun goToDashboard() {
        val intent = Intent(this, DashboardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        goToDashboard()
    }
}