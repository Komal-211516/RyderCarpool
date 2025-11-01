package com.rydercarpool.models

data class User(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val guardianNumber: String = "",
    val createdAt: com.google.firebase.Timestamp = com.google.firebase.Timestamp.now()
)