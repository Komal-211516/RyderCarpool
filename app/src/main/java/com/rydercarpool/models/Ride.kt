package com.rydercarpool.models

data class Ride(
    val id: String = "",
    val driverId: String = "",
    val passengerId: String = "",
    val pickupLocation: String = "",
    val dropoffLocation: String = "",
    val fare: Double = 0.0,
    val status: String = "",
    val timestamp: Long = 0L
)
