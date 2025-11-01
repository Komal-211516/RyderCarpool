package com.rydercarpool.services

import com.rydercarpool.models.Ride

class RideService {
    
    private val bookedRides = mutableListOf<Ride>()
    
    fun bookRide(ride: Ride): Boolean {
        return try {
            // Add the ride to the list (in real app, this would be a database operation)
            bookedRides.add(ride)
            true
        } catch (e: Exception) {
            false
        }
    }
    
    fun getBookedRides(): List<Ride> {
        return bookedRides.toList()
    }
    
    fun cancelRide(rideId: String): Boolean {
        val ride = bookedRides.find { it.rideId == rideId }
        return ride?.let {
            bookedRides.remove(it)
            true
        } ?: false
    }
}