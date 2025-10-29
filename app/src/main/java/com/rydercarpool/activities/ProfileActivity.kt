// Create new: activities/ProfileActivity.kt
class ProfileActivity : AppCompatActivity() {
    
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        
        loadUserProfile()
    }
    
    private fun loadUserProfile() {
        val userId = auth.currentUser?.uid ?: return
        
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject(User::class.java)
                    // Display user data in UI
                    findViewById<TextView>(R.id.tvUserName).text = user?.name ?: "No Name"
                    findViewById<TextView>(R.id.tvUserEmail).text = user?.email ?: "No Email"
                }
            }
    }
}