package pe.edu.ulima.pm.ulecommerce

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import pe.edu.ulima.pm.ulecommerce.adapters.ProductsSliderAdapter
import pe.edu.ulima.pm.ulecommerce.models.beans.Product
import pe.edu.ulima.pm.ulecommerce.models.managers.OnGetProductsDone
import pe.edu.ulima.pm.ulecommerce.models.managers.ProductsManager
import java.util.ArrayList

class ProductsActivity : AppCompatActivity(), OnGetProductsDone{
    var pager : ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        ProductsManager.getInstance().getProducts(this, this)

        pager = findViewById(R.id.pager)

    }

    override fun onSuccess(products: ArrayList<Product>) {
        pager!!.adapter = ProductsSliderAdapter(supportFragmentManager, products)
    }

    override fun onError(msg: String) {
        Log.e("ProductsActivity", msg)
    }
}