package com.rydercarpool.models

import com.google.firebase.Timestamp

data class Rating(
    val ratingId: String = "",
    val rideId: String = "",
    val fromUserId: String = "",
    val fromUserName: String = "",
    val toUserId: String = "",
    val toUserName: String = "",
    val rating: Float = 0f,
    val comment: String = "",
    val userType: String = "",
    val timestamp: Timestamp? = null
)
