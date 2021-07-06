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

    fun addProduct(name : String, price : Float, callback : ()-> Unit) {
        db.collection("productos")
            .add(hashMapOf(
                "name" to name,
                "price" to price,
                "url" to "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-12-pro-blue-hero"
            ))
            .addOnSuccessListener {
                Log.i("FirebaseProductDAO", it.id)
                callback()
            }
            .addOnFailureListener {
                Log.e("FirebaseProductDAO", it.message!!)
                callback()
            }
    }
}