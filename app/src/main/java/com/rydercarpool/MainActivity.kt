import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.rydercarpool.activities.LiveLocationActivity
import com.rydercarpool.activities.RatingActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupButton = findViewById<Button>(R.id.signupButton)
        val safetyButton = findViewById<Button>(R.id.safetyButton)
        
        // Consolidated button finding
        val startRideButton = findViewById<Button>(R.id.btnStartRide) ?: findViewById<Button>(R.id.start_ride_button)
        val liveLocationButton = findViewById<Button>(R.id.btnLiveLocation) ?: findViewById<Button>(R.id.live_location_button)
        val ratingsButton = findViewById<Button>(R.id.btnRatings) ?: findViewById<Button>(R.id.ratings_button)

        // Set up click listeners
        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        safetyButton.setOnClickListener {
            openSafetyFeatures()
        }

        startRideButton?.setOnClickListener {
            Toast.makeText(this, "Start Ride clicked", Toast.LENGTH_SHORT).show()
        }

        liveLocationButton?.setOnClickListener {
            val intent = Intent(this, LiveLocationActivity::class.java)
            startActivity(intent)
        }

        ratingsButton?.setOnClickListener {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }

        // If no buttons found, show a message
        if (startRideButton == null && liveLocationButton == null && ratingsButton == null) {
            Toast.makeText(this, "No buttons found in layout", Toast.LENGTH_LONG).show()
        }
    }

    private fun openSafetyFeatures() {
        val intent = Intent(this, LiveLocationActivity::class.java)
        startActivity(intent)
    }
}