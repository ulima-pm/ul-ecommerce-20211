package pe.edu.ulima.pm.ulecommerce.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulecommerce.ProductsActivity
import pe.edu.ulima.pm.ulecommerce.R

class ProductsFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val butGoActivityProducts = view!!.findViewById<Button>(R.id.butGoActivityProducts);
        // TODO: Debe mejorarse
        butGoActivityProducts.setOnClickListener { _ : View ->
            val intent = Intent(activity, ProductsActivity::class.java)
            startActivity(intent)
        }
    }
}