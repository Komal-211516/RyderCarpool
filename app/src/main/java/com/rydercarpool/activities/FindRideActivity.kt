package com.rydercarpool.activities

import android.content.Context // <-- Add this line
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R
import com.rydercarpool.models.Ride
import java.text.SimpleDateFormat
import java.util.*

// ... rest of your code


class FindRideActivity : AppCompatActivity() {

    private lateinit var ridesListView: ListView
    private lateinit var rides: List<Ride>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_ride)

        ridesListView = findViewById(R.id.ridesListView)
        findAndDisplayRides()
    }

    private fun findAndDisplayRides() {
        rides = listOf(
            Ride(
                id = "ride_001",
                fromLocation = "New York",
                toLocation = "Boston",
                date = "2025-11-15",  // String - matches your Ride model
                time = "10:00 AM",
                availableSeats = 3,
                price = 25.50,
                driverName = "John Smith",
                driverRating = 4.5,
                totalRatings = 120,
                guardianPhone = "+1234567890",
                rideType = "Standard"
            ),
            Ride(
                id = "ride_002",
                fromLocation = "Chicago",
                toLocation = "Detroit",
                date = "2025-11-16",  // String - matches your Ride model
                time = "02:30 PM",
                availableSeats = 2,
                price = 30.00,
                driverName = "Sarah Johnson",
                driverRating = 4.8,
                totalRatings = 95,
                guardianPhone = "+1987654321",
                rideType = "Premium"
            )
        )

        displayRides(rides)
    }

    private fun displayRides(rides: List<Ride>) {
        val adapter = RideAdapter(this, rides)
        ridesListView.adapter = adapter

        ridesListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedRide = rides[position]
            Toast.makeText(
                this,
                "Selected: ${selectedRide.fromLocation} to ${selectedRide.toLocation}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Custom adapter class
    private inner class RideAdapter(context: Context, private val rides: List<Ride>) :
        ArrayAdapter<Ride>(context, R.layout.ride_list_item, rides) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.ride_list_item, parent, false)

            val ride = getItem(position)!!

            // Format date for display (from "2025-11-15" to "Nov 15, 2025")
            val displayDate = formatDateForDisplay(ride.date)

            view.findViewById<TextView>(R.id.tvLocations).text =
                "${ride.fromLocation} → ${ride.toLocation}"
            view.findViewById<TextView>(R.id.tvPrice).text =
                "$${ride.price}"
            view.findViewById<TextView>(R.id.tvDate).text = displayDate
            view.findViewById<TextView>(R.id.tvTime).text = ride.time
            view.findViewById<TextView>(R.id.tvDriver).text = ride.driverName
            view.findViewById<TextView>(R.id.tvRating).text =
                "${ride.driverRating} ★ (${ride.totalRatings})"
            view.findViewById<TextView>(R.id.tvSeats).text =
                "${ride.availableSeats} seats available"
            view.findViewById<TextView>(R.id.tvRideType).text = ride.rideType

            return view
        }

        private fun formatDateForDisplay(dateString: String): String {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                val date = inputFormat.parse(dateString)
                outputFormat.format(date!!)
            } catch (e: Exception) {
                dateString // Return original if parsing fails
            }
        }
    }
}