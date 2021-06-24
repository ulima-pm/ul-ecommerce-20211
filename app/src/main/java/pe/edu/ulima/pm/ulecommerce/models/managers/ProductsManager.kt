package pe.edu.ulima.pm.ulecommerce.models.managers

import android.content.Context
import androidx.room.Room
import pe.edu.ulima.pm.ulecommerce.models.beans.Product
import pe.edu.ulima.pm.ulecommerce.models.dao.DevicesService
import pe.edu.ulima.pm.ulecommerce.models.persistence.AppDatabase
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

interface OnGetProductsDone {
    fun onSuccess(products : java.util.ArrayList<Product>)
    fun onError(msg : String)
}

// Fachada
// Singleton
class ProductsManager {

    // Se ponen los atributos y metodos estaticos
    companion object {
        private var instance : ProductsManager? = null

        fun getInstance() : ProductsManager {
            if (instance == null){
                instance = ProductsManager()
            }
            return instance!!
        }
    }

    fun getProducts(callback : OnGetProductsDone, context : Context)  {
        val productDAO = FirebaseManager.getInstance().getProductDAO()
        productDAO.getProducts({
            callback.onSuccess(it as ArrayList<Product>)
        })

        //if (isFirstTimeGetProducts((context))) {
        //}



    }

    private fun getProductsRetrofit(callback : OnGetProductsDone, context : Context) {
        if (isFirstTimeGetProducts(context)) {
            val retrofit = ConnectionManager.getInstance().getRetrofit()

            val devicesService = retrofit.create<DevicesService>()
            devicesService.getDevices().enqueue(object  : Callback<java.util.ArrayList<Product>> {
                override fun onResponse(
                    call: Call<java.util.ArrayList<Product>>,
                    response: Response<java.util.ArrayList<Product>>
                ) {
                    if (response.body() != null) {
                        saveProductsRoom(response.body()!!, context, { products : ArrayList<Product> ->
                            val edit = context.getSharedPreferences(
                                "USERS_DATA", Context.MODE_PRIVATE).edit()
                            edit.putBoolean("IS_FIRST_TIME_PRODUCTS", false)
                            edit.commit()
                            callback.onSuccess(products)
                        })

                    }else {
                        callback.onError("Arraylist nulo")
                    }
                }

                override fun onFailure(call: Call<java.util.ArrayList<Product>>, t: Throwable) {
                    callback.onError(t.message!!)
                }
            })
        }else {
            // Obtener data de sqlite
            getProductsRoom(context, {products : java.util.ArrayList<Product> ->
                callback.onSuccess(products)
            })
        }
    }

    private fun getProductsRoom(context: Context, callback: (ArrayList<Product>) -> Unit) {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "ULECOMMERCE_DB"
        ).fallbackToDestructiveMigration().build()

        Thread {
            val productDAO = db.productDAO()

            val productList = java.util.ArrayList<Product>()
            productDAO.findAll().forEach { p : pe.edu.ulima.pm.ulecommerce.models.persistence.entities.Product ->
                productList.add(Product(
                    p.id,
                    p.name,
                    p.price,
                    p.url
                ))
            }
            callback(productList)
        }.start()

    }

    private fun isFirstTimeGetProducts(context: Context): Boolean {
        return context.getSharedPreferences("USERS_DATA",
            Context.MODE_PRIVATE).getBoolean("IS_FIRST_TIME_PRODUCTS", true)
    }

    private fun saveProductsRoom(products: ArrayList<Product>,
                                 context : Context,
                                 callback : (products : ArrayList<Product>) -> Unit) {
        val db = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "ULECOMMERCE_DB"
        ).fallbackToDestructiveMigration().build()

        Thread {
            val productDAO = db.productDAO()
            products.forEach { p : Product ->
                productDAO.insert(pe.edu.ulima.pm.ulecommerce.models.persistence.entities.Product(
                    p.id,
                    p.name,
                    p.price,
                    p.url
                ))
            }
            callback(products)
        }.start()


    }
}