package com.rydercarpool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import com.rydercarpool.R

class RatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val btnSubmitRating = findViewById<Button>(R.id.btnSubmitRating)

        btnSubmitRating.setOnClickListener {
            val ratingValue = ratingBar.rating
            Toast.makeText(this, "Rating submitted: $ratingValue stars", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}