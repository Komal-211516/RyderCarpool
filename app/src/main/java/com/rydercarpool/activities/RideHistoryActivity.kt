package com.rydercarpool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rydercarpool.adapters.RideHistoryAdapter
import com.rydercarpool.models.Ride
import com.rydercarpool.R

class RideHistoryActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RideHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ride_history)

        initializeViews()
        setupRecyclerView()
    }

    private fun initializeViews() {
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun setupRecyclerView() {
        // Create sample data - in real app, this would come from your database
        val sampleRides = listOf(
            Ride(
                rideId = "1",
                pickupLocation = "New York",
                destination = "Boston",
                fare = 45.0,
                rideDate = "2024-01-15",
                rideTime = "10:00 AM",
                driverName = "John Doe",
                vehicleModel = "Toyota Camry",
                status = "Completed"
            ),
            Ride(
                rideId = "2",
                pickupLocation = "Boston", 
                destination = "New York",
                fare = 50.0,
                rideDate = "2024-01-20",
                rideTime = "2:00 PM",
                driverName = "Jane Smith",
                vehicleModel = "Honda Civic",
                status = "Upcoming"
            )
        )

        adapter = RideHistoryAdapter(sampleRides)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}