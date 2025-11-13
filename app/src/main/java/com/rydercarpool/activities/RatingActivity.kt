package com.rydercarpool.activities

import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rydercarpool.R

class RatingActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var tvRateUser: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var btnSubmit: Button

    private var toUserId: String = ""
    private var toUserName: String = ""
    private var userType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        // Initialize Firebase
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Get data from intent
        toUserId = intent.getStringExtra("toUserId") ?: ""
        toUserName = intent.getStringExtra("toUserName") ?: "User"
        userType = intent.getStringExtra("userType") ?: "driver"

        initializeViews()
        setupClickListeners()
    }

    private fun initializeViews() {
        tvRateUser = findViewById(R.id.tvRateUser)
        ratingBar = findViewById(R.id.ratingBar)
        btnSubmit = findViewById(R.id.btnSubmit)

        tvRateUser.text = "Rate $toUserName"
    }

    private fun setupClickListeners() {
        btnSubmit.setOnClickListener {
            submitRating()
        }
    }

    private fun submitRating() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(this, "Please login to submit rating", Toast.LENGTH_SHORT).show()
            return
        }

        val ratingValue = ratingBar.rating
        if (ratingValue == 0f) {
            Toast.makeText(this, "Please select a rating", Toast.LENGTH_SHORT).show()
            return
        }

        // Create rating object
        val rating = Rating(
            ratingId = firestore.collection("ratings").document().id,
            fromUserId = currentUser.uid,
            fromUserName = currentUser.displayName ?: "Anonymous User",
            toUserId = toUserId,
            toUserName = toUserName,
            rating = ratingValue,
            userType = userType,
            timestamp = Timestamp.now()
        )

        // Save to Firestore
        firestore.collection("ratings")
            .document(rating.ratingId)
            .set(rating)
            .addOnSuccessListener {
                Toast.makeText(this, "Rating submitted successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to submit rating: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    data class Rating(
        val ratingId: String = "",
        val fromUserId: String = "",
        val fromUserName: String = "",
        val toUserId: String = "",
        val toUserName: String = "",
        val rating: Float = 0f,
        val userType: String = "",
        val timestamp: Timestamp = Timestamp.now()
    )
}