package pe.edu.ulima.pm.ulecommerce.models.managers

import pe.edu.ulima.pm.ulecommerce.models.beans.Product
import pe.edu.ulima.pm.ulecommerce.models.dao.DevicesService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

// Fachada
// Singleton
class ProductsManager {
    private var productList : ArrayList<Product> = ArrayList()

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

    private constructor(){
        productList.add(Product(1, "Iphone 12", 500f,"http://i.blogs.es/adf267/image-2020-10-13-20-20-17/450_1000.jpg"))
        productList.add(Product(2, "Pixel 5", 400f, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-pro-family-hero"))
        productList.add(Product(3, "Samsung Galaxy S21", 500f, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-pro-family-hero"))
    }

    fun getProducts() : ArrayList<Product> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://60b83e68b54b0a0017c03380.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val devicesService = retrofit.create<DevicesService>()
        devicesService.getDevices().enqueue(object  : Callback<java.util.ArrayList<Product>> {
            override fun onResponse(
                call: Call<java.util.ArrayList<Product>>,
                response: Response<java.util.ArrayList<Product>>
            ) {
                TODO("Not yet implemented")

            }

            override fun onFailure(call: Call<java.util.ArrayList<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

    fun addProduct(name : String, price : Float, image: String) : Unit {
        val product = Product(productList.size+1, name, price, image)
        productList.add(product)
    }
}