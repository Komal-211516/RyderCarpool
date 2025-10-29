// Create new: services/RideService.kt
class RideService {
    private val db = FirebaseFirestore.getInstance()
    private val ridesCollection = db.collection("rides")
    
    fun createRide(ride: Ride, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        ridesCollection.document(ride.rideId)
            .set(ride)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e -> onFailure(e.message ?: "Unknown error") }
    }
    
    fun getAvailableRides(pickup: String, onSuccess: (List<Ride>) -> Unit, onFailure: (String) -> Unit) {
        ridesCollection
            .whereEqualTo("status", "available")
            .whereEqualTo("pickupLocation", pickup)
            .get()
            .addOnSuccessListener { documents ->
                val rides = documents.map { doc ->
                    doc.toObject(Ride::class.java)
                }
                onSuccess(rides)
            }
            .addOnFailureListener { e -> onFailure(e.message ?: "Unknown error") }
    }
}