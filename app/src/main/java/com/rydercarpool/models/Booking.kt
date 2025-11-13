package com.rydercarpool.models

data class Booking(
    val id: String = "",
    val rideId: String = "",
    val passengerId: String = "",
    val passengerName: String = "",
    val passengerPhone: String = "",
    val guardianName: String = "",
    val guardianPhone: String = "",
    val fromLocation: String = "",
    val toLocation: String = "",
    val date: String = "",
    val scheduledTime: String = "",
    val estimatedArrivalTime: Long = 0,
    val actualArrivalTime: Long = 0,
    val seatsBooked: Int = 1,
    val totalPrice: Double = 0.0,
    val status: String = "confirmed", // confirmed, in_progress, completed, rated
    val driverId: String = "",
    val driverName: String = "",
    val driverPhone: String = "",
    val carDetails: String = "",
    val passengerRated: Boolean = false,
    val driverRated: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)