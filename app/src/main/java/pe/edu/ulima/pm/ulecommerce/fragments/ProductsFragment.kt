package pe.edu.ulima.pm.ulecommerce.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulecommerce.ProductsActivity
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.adapters.ProductsAdapter
import pe.edu.ulima.pm.ulecommerce.models.ProductsManager

class ProductsFragment : Fragment(){

    var lviProducts : ListView? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val butGoActivityProducts = view!!.findViewById<Button>(R.id.butGoActivityProducts);

        lviProducts = view!!.findViewById(R.id.lviProducts)
        val productsList = ProductsManager.getInstance().getProducts()
        val productsAdapter = ProductsAdapter(activity as Context, productsList)
        lviProducts!!.adapter = productsAdapter


        // TODO: Debe mejorarse
        butGoActivityProducts.setOnClickListener { _ : View ->
            val intent = Intent(activity, ProductsActivity::class.java)
            startActivity(intent)
        }
    }
}