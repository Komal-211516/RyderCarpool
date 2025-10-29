// Create new: activities/BookRideActivity.kt
class BookRideActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_ride)
        
        val btnFindRide = findViewById<Button>(R.id.btnFindRide)
        val editPickup = findViewById<EditText>(R.id.editPickup)
        val editDestination = findViewById<EditText>(R.id.editDestination)
        
        btnFindRide.setOnClickListener {
            val pickup = editPickup.text.toString()
            val destination = editDestination.text.toString()
            
            if (pickup.isNotEmpty() && destination.isNotEmpty()) {
                findAvailableRides(pickup, destination)
            } else {
                Toast.makeText(this, "Please enter pickup and destination", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun findAvailableRides(pickup: String, destination: String) {
        // TODO: Query Firestore for available rides
        Toast.makeText(this, "Finding rides from $pickup to $destination", Toast.LENGTH_SHORT).show()
    }
}