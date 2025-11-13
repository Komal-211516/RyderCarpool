package com.rydercarpool.models

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val guardianName: String = "",
    val guardianPhone: String = "",
    val createdAt: Long = System.currentTimeMillis()
)