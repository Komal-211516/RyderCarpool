package com.rydercarpool.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.rydercarpool.R
import com.rydercarpool.models.Rating

class RatingActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var etComment: EditText
    private lateinit var btnSubmitRating: Button
    private lateinit var tvRateUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        ratingBar = findViewById(R.id.ratingBar)
        etComment = findViewById(R.id.etComment)
        btnSubmitRating = findViewById(R.id.btnSubmitRating)
        tvRateUser = findViewById(R.id.tvRateUser)

        val rideId = intent.getStringExtra("ride_id") ?: ""
        val otherUserName = intent.getStringExtra("other_user_name") ?: "User"
        val otherUserId = intent.getStringExtra("other_user_id") ?: ""
        val userType = intent.getStringExtra("user_type") ?: ""

        tvRateUser.text = "Rate $otherUserName"
        
        btnSubmitRating.setOnClickListener {
            submitRating(rideId, otherUserId, userType)
        }
    }

    private fun submitRating(rideId: String, otherUserId: String, userType: String) {
        val ratingValue = ratingBar.rating
        val comment = etComment.text.toString().trim()

        if (ratingValue == 0f) {
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show()
            return
        }

        val rating = Rating(
            rideId = rideId,
            fromUserId = getCurrentUserId(),
            toUserId = otherUserId,
            rating = ratingValue,
            comment = comment,
            timestamp = System.currentTimeMillis()
        )

        saveRating(rating)
        Toast.makeText(this, "Rating submitted successfully!", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun getCurrentUserId(): String {
        return "current_user_id"
    }

    private fun saveRating(rating: Rating) {
        println("Rating to save: $rating")
    }
}