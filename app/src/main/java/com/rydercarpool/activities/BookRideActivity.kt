package com.rydercarpool.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.rydercarpool.R
import com.rydercarpool.models.Ride
import java.text.SimpleDateFormat
import java.util.*

class BookRideActivity : AppCompatActivity() {

    private lateinit var etFromLocation: EditText
    private lateinit var etToLocation: EditText
    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView
    private lateinit var etGuardianPhone: EditText
    private lateinit var btnConfirmBooking: Button

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_ride)

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        etFromLocation = findViewById(R.id.etFromLocation)
        etToLocation = findViewById(R.id.etToLocation)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)
        etGuardianPhone = findViewById(R.id.etGuardianPhone)
        btnConfirmBooking = findViewById(R.id.btnConfirmBooking)
    }

    private fun setupClickListeners() {
        // Date picker
        findViewById<LinearLayout>(R.id.btnSelectDate).setOnClickListener {
            showDatePicker()
        }

        // Time picker
        findViewById<LinearLayout>(R.id.btnSelectTime).setOnClickListener {
            showTimePicker()
        }

        // Confirm booking
        btnConfirmBooking.setOnClickListener {
            confirmBooking()
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateDisplay()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                updateTimeDisplay()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePicker.show()
    }

    private fun updateDateDisplay() {
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        tvSelectedDate.text = dateFormat.format(calendar.time)
        tvSelectedDate.setTextColor(resources.getColor(android.R.color.black))
    }

    private fun updateTimeDisplay() {
        val timeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        tvSelectedTime.text = timeFormat.format(calendar.time)
        tvSelectedTime.setTextColor(resources.getColor(android.R.color.black))
    }

    private fun confirmBooking() {
        val fromLocation = etFromLocation.text.toString().trim()
        val toLocation = etToLocation.text.toString().trim()
        val guardianPhone = etGuardianPhone.text.toString().trim()

        // Validation
        if (fromLocation.isEmpty()) {
            etFromLocation.error = "Please enter pickup location"
            return
        }

        if (toLocation.isEmpty()) {
            etToLocation.error = "Please enter destination"
            return
        }

        if (guardianPhone.isEmpty()) {
            etGuardianPhone.error = "Please enter guardian phone number"
            return
        }

        if (tvSelectedDate.text.toString() == "Select date") {
            Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show()
            return
        }

        if (tvSelectedTime.text.toString() == "Select time") {
            Toast.makeText(this, "Please select time", Toast.LENGTH_SHORT).show()
            return
        }

        // Create ride booking
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        val ride = Ride(
            id = UUID.randomUUID().toString(),
            fromLocation = fromLocation,
            toLocation = toLocation,
            date = dateFormat.format(calendar.time),
            time = timeFormat.format(calendar.time),
            availableSeats = 1,
            price = calculatePrice(fromLocation, toLocation),
            driverName = "To be assigned",
            driverRating = 0.0,
            totalRatings = 0,
            guardianPhone = guardianPhone,
            rideType = "Standard"
        )

        // Process the booking and navigate
        processBooking(ride)
    }

    private fun calculatePrice(from: String, to: String): Double {
        // Simple price calculation - replace with your logic
        return when {
            from.contains("meerpet", ignoreCase = true) && to.contains("jillaguida", ignoreCase = true) -> 150.0
            else -> 25.0
        }
    }

    private fun processBooking(ride: Ride) {
        // Show confirmation message
        Toast.makeText(this, "Booking confirmed!", Toast.LENGTH_SHORT).show()

        // Navigate to Booking Confirmation Activity
        val intent = Intent(this, BookingConfirmationActivity::class.java).apply {
            putExtra("ride", ride)
            putExtra("booking_id", UUID.randomUUID().toString())
            putExtra("booking_time", SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault()).format(Date()))
        }
        startActivity(intent)

        // Optional: Finish current activity so user can't go back
        finish()
    }
}