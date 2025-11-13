package com.rydercarpool.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.databinding.ActivityOfferRideBinding
import java.util.Calendar

class OfferRideActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOfferRideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOfferRideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbarOfferRide.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupClickListeners() {
        binding.btnSubmit.setOnClickListener {
            if (validateForm()) {
                offerRide()
            }
        }

        binding.etDate.setOnClickListener {
            showDatePicker()
        }

        binding.etTime.setOnClickListener {
            showTimePicker()
        }
    }

    private fun validateForm(): Boolean {
        // ADD FROM/TO LOCATION VALIDATION
        val fromLocation = binding.etFromLocation.text?.toString()?.trim() ?: ""
        val toLocation = binding.etToLocation.text?.toString()?.trim() ?: ""
        val date = binding.etDate.text?.toString()?.trim() ?: ""
        val time = binding.etTime.text?.toString()?.trim() ?: ""
        val seats = binding.etSeats.text?.toString()?.trim() ?: ""
        val price = binding.etPrice.text?.toString()?.trim() ?: ""
        val carModel = binding.etCarModel.text?.toString()?.trim() ?: ""
        val carColor = binding.etCarColor.text?.toString()?.trim() ?: ""

        return when {
            // ADD FROM LOCATION CHECK
            fromLocation.isEmpty() -> {
                Toast.makeText(this, "Please enter starting location", Toast.LENGTH_SHORT).show()
                false
            }
            // ADD TO LOCATION CHECK
            toLocation.isEmpty() -> {
                Toast.makeText(this, "Please enter destination", Toast.LENGTH_SHORT).show()
                false
            }
            date.isEmpty() -> {
                Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show()
                false
            }
            time.isEmpty() -> {
                Toast.makeText(this, "Please select time", Toast.LENGTH_SHORT).show()
                false
            }
            seats.isEmpty() -> {
                Toast.makeText(this, "Please enter number of seats", Toast.LENGTH_SHORT).show()
                false
            }
            price.isEmpty() -> {
                Toast.makeText(this, "Please enter price per seat", Toast.LENGTH_SHORT).show()
                false
            }
            carModel.isEmpty() -> {
                Toast.makeText(this, "Please enter car model", Toast.LENGTH_SHORT).show()
                false
            }
            carColor.isEmpty() -> {
                Toast.makeText(this, "Please enter car color", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }

    private fun offerRide() {
        // GET FROM/TO LOCATIONS
        val fromLocation = binding.etFromLocation.text?.toString()?.trim() ?: ""
        val toLocation = binding.etToLocation.text?.toString()?.trim() ?: ""
        val date = binding.etDate.text?.toString()?.trim() ?: ""
        val time = binding.etTime.text?.toString()?.trim() ?: ""
        val seats = binding.etSeats.text?.toString()?.trim() ?: "0"
        val price = binding.etPrice.text?.toString()?.trim() ?: "0"
        val carModel = binding.etCarModel.text?.toString()?.trim() ?: ""
        val carColor = binding.etCarColor.text?.toString()?.trim() ?: ""

        // SHOW CONFIRMATION WITH FROM/TO DETAILS
        val rideDetails = """
            Ride Offer Details:
            From: $fromLocation
            To: $toLocation
            Date: $date
            Time: $time
            Seats: $seats
            Price: $$price per seat
            Car: $carModel ($carColor)
        """.trimIndent()

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Confirm Ride Offer")
            .setMessage(rideDetails)
            .setPositiveButton("Confirm") { dialog, which ->
                saveRideOffer(fromLocation, toLocation, date, time, seats.toInt(), price.toDouble(), carModel, carColor)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveRideOffer(
        fromLocation: String,
        toLocation: String,
        date: String,
        time: String,
        seats: Int,
        price: Double,
        carModel: String,
        carColor: String
    ) {
        Toast.makeText(this, "Ride offered successfully!", Toast.LENGTH_SHORT).show()

        try {
            val intent = Intent(this, RidePostedActivity::class.java)
            // PASS FROM/TO LOCATIONS
            intent.putExtra("from_location", fromLocation)
            intent.putExtra("to_location", toLocation)
            intent.putExtra("date", date)
            intent.putExtra("time", time)
            intent.putExtra("seats", seats)
            intent.putExtra("price", price)
            startActivity(intent)
        } catch (e: Exception) {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        finish()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedMonth + 1}/$selectedDay/$selectedYear"
                binding.etDate.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000
        datePicker.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePicker = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val amPm = if (selectedHour < 12) "AM" else "PM"
                val displayHour = if (selectedHour > 12) selectedHour - 12 else if (selectedHour == 0) 12 else selectedHour
                val selectedTime = "$displayHour:${String.format("%02d", selectedMinute)} $amPm"
                binding.etTime.setText(selectedTime)
            },
            hour,
            minute,
            false
        )
        timePicker.show()
    }
}