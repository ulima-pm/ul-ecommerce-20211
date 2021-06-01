package pe.edu.ulima.pm.ulecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import pe.edu.ulima.pm.ulecommerce.adapters.ProductsSliderAdapter
import pe.edu.ulima.pm.ulecommerce.models.Product
import pe.edu.ulima.pm.ulecommerce.models.ProductsManager

class ProductsActivity : AppCompatActivity(){
    var pager : ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val pm = ProductsManager.getInstance()

        pager = findViewById(R.id.pager)
        pager!!.adapter = ProductsSliderAdapter(supportFragmentManager, pm.getProducts())
    }
}