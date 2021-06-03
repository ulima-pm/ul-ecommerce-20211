package pe.edu.ulima.pm.ulecommerce.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ulecommerce.ProductsActivity
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.adapters.OnProductItemClickListener
import pe.edu.ulima.pm.ulecommerce.adapters.ProductsRVAdapter
import pe.edu.ulima.pm.ulecommerce.models.beans.Product
import pe.edu.ulima.pm.ulecommerce.models.managers.ProductsManager

class ProductsFragment : Fragment(), OnProductItemClickListener{

    //var lviProducts : ListView? = null
    var rviProducts : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val butGoActivityProducts = view!!.findViewById<Button>(R.id.butGoActivityProducts);

        rviProducts = view!!.findViewById(R.id.rviProducts)
        val productsList = ProductsManager.getInstance().getProducts()
        //val productsAdapter = ProductsAdapter(activity as Context, productsList)
        val productsRVAdapter = ProductsRVAdapter(productsList, this, activity!!)
        //lviProducts!!.adapter = productsAdapter
        rviProducts!!.adapter = productsRVAdapter


        // TODO: Debe mejorarse
        butGoActivityProducts.setOnClickListener { _ : View ->
            val intent = Intent(activity, ProductsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onClick(product: Product) {
        Toast.makeText(context, product.name, Toast.LENGTH_LONG).show()
    }
}