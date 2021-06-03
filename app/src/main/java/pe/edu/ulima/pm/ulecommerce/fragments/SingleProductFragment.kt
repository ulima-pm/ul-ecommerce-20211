package pe.edu.ulima.pm.ulecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.models.beans.Product

class SingleProductFragment(val product : Product) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_single_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view!!.findViewById<TextView>(R.id.tviProductId).text = product.id.toString()
        view!!.findViewById<TextView>(R.id.tviProductName).setText(product.name)
        view!!.findViewById<TextView>(R.id.tviProductPrice).setText(product.price.toString())
    }
}