package com.rydercarpool.models

data class Rating(
    val id: String = "",
    val rideId: String = "",
    val fromUserId: String = "",
    val toUserId: String = "",
    val rating: Float = 0f,
    val comment: String = "",
    val timestamp: Long = 0L
)
