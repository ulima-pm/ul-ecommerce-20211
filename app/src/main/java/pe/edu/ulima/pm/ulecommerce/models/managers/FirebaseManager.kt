package pe.edu.ulima.pm.ulecommerce.models.managers

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pe.edu.ulima.pm.ulecommerce.models.firebase.FirebaseProductDAO

// Singleton
class FirebaseManager {
    private val db = Firebase.firestore;

    companion object {
        private var instance : FirebaseManager? = null

        fun getInstance() : FirebaseManager {
            if (instance == null) {
                instance = FirebaseManager()
            }
            return instance!!
        }
    }

    fun getDB(): FirebaseFirestore {
        return db
    }

    fun getProductDAO() : FirebaseProductDAO {
        return FirebaseProductDAO(db)
    }
}