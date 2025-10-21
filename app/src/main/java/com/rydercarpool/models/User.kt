package com.rydercarpool.models

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val userType: String = "",
    val rating: Double = 0.0,
    val totalRides: Int = 0
)
