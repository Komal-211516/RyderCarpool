package com.rydercarpool.models

import java.io.Serializable

data class Ride(
    val id: String,
    val fromLocation: String,
    val toLocation: String,
    val date: String,
    val time: String,
    val availableSeats: Int,
    val price: Double,
    val driverName: String,
    val driverRating: Double,
    val totalRatings: Int,
    val guardianPhone: String,
    val rideType: String
) : Serializable  // Add this