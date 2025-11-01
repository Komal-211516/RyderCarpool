package com.rydercarpool.models

import java.io.Serializable

data class Ride(
    val rideId: String,
    val pickupLocation: String,
    val destination: String,
    val fare: Double,
    val rideDate: String,
    val rideTime: String,
    val driverName: String,
    val vehicleModel: String,
    val status: String
) : Serializable