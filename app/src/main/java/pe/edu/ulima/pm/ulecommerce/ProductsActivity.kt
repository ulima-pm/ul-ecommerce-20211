package pe.edu.ulima.pm.ulecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import pe.edu.ulima.pm.ulecommerce.adapters.ProductsSliderAdapter
import pe.edu.ulima.pm.ulecommerce.models.Product

class ProductsActivity : AppCompatActivity(){
    var pager : ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        pager = findViewById(R.id.pager)
        pager!!.adapter = ProductsSliderAdapter(supportFragmentManager,createProducts())
    }

    fun createProducts() : ArrayList<Product>
    {
        val productList = ArrayList<Product>()
        productList.add(Product(1, "Iphone 12", 500f))
        productList.add(Product(2, "Pixel 5", 400f))
        productList.add(Product(3, "Samsung Galaxy S21", 500f))

        return productList
    }
}