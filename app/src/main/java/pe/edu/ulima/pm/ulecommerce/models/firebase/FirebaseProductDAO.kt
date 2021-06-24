package pe.edu.ulima.pm.ulecommerce.models.firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import pe.edu.ulima.pm.ulecommerce.models.beans.Product

class FirebaseProductDAO(val db : FirebaseFirestore) {

    fun getProducts(callback : (List<Product>) -> Unit) {
        db.collection("productos")
            .get()
            .addOnSuccessListener { documents ->
                val products = documents.map { doc ->
                    Product(
                        0,
                        doc.data["name"].toString(),
                        doc.data["price"].toString().toFloat(),
                        doc.data["url"].toString()
                    )
                }
                callback(products)
            }.addOnFailureListener { exception ->
                Log.e("FirebaseProductDAO", exception.message!!);
            }
    }
}