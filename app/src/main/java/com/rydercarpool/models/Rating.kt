package com.rydercarpool.models

data class Rating(
    val ratingId: String = "",
    val rideId: String = "",
    val fromUserId: String = "",
    val fromUserName: String = "",
    val toUserId: String = "",
    val toUserName: String = "",
    val rating: Double = 0.0,
    val comment: String = "",
    val userType: String = "", // "driver" or "passenger"
    val timestamp: com.google.firebase.Timestamp = com.google.firebase.Timestamp.now()
)