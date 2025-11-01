package com.rydercarpool.activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rydercarpool.R
import com.rydercarpool.models.Ride
import com.rydercarpool.services.RideService
import java.text.SimpleDateFormat
import java.util.*

class BookRideActivity : AppCompatActivity() {

    private lateinit var editPickup: EditText
    private lateinit var editDestination: EditText
    private lateinit var btnFindRide: Button
    private lateinit var recyclerViewRides: RecyclerView
    private lateinit var rideService: RideService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_ride)

        initializeViews()
        setupClickListeners()
        
        rideService = RideService()
    }

    private fun initializeViews() {
        // Use the exact IDs from your layout
        editPickup = findViewById(R.id.editPickup)
        editDestination = findViewById(R.id.editDestination)
        btnFindRide = findViewById(R.id.btnFindRide)
        recyclerViewRides = findViewById(R.id.recyclerViewRides)
    }

    private fun setupClickListeners() {
        btnFindRide.setOnClickListener {
            findRide()
        }
    }

    private fun findRide() {
        val pickupLocation = editPickup.text.toString().trim()
        val destination = editDestination.text.toString().trim()

        if (pickupLocation.isEmpty() || destination.isEmpty()) {
            Toast.makeText(this, "Please enter both pickup and destination locations", Toast.LENGTH_SHORT).show()
            return
        }

        // Calculate fare based on locations
        val calculatedFare = calculateFare(pickupLocation, destination)
        
        // Show available rides or book directly
        val ride = Ride(
            rideId = generateRideId(),
            pickupLocation = pickupLocation,
            destination = destination,
            fare = calculatedFare,
            rideDate = getCurrentDate(),
            rideTime = getCurrentTime(),
            driverName = "Available Driver",
            vehicleModel = "Standard Vehicle",
            status = "Searching"
        )

        val bookingResult = rideService.bookRide(ride)
        
        if (bookingResult) {
            Toast.makeText(this, "Ride found! Fare: $${"%.2f".format(calculatedFare)}", Toast.LENGTH_LONG).show()
            // You can show available rides in recyclerViewRides here
            clearForm()
        } else {
            Toast.makeText(this, "No rides available. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateFare(pickup: String, destination: String): Double {
        // Simple fare calculation
        val baseFare = 5.0
        val distanceFare = (pickup.length + destination.length) * 0.5
        return baseFare + distanceFare
    }

    private fun clearForm() {
        editPickup.text.clear()
        editDestination.text.clear()
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun generateRideId(): String {
        return "RIDE_${System.currentTimeMillis()}"
    }
}