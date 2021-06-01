package pe.edu.ulima.pm.ulecommerce.models

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
        productList.add(Product(1, "Iphone 12", 500f,", https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-pro-family-hero"))
        productList.add(Product(2, "Pixel 5", 400f, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-pro-family-hero"))
        productList.add(Product(3, "Samsung Galaxy S21", 500f, "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-12-pro-family-hero"))
    }

    fun getProducts() : ArrayList<Product> {
        return productList
    }

    fun addProduct(name : String, price : Float, image: String) : Unit {
        val product = Product(productList.size+1, name, price, image)
        productList.add(product)
    }
}