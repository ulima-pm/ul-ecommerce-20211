package pe.edu.ulima.pm.ulecommerce.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.models.managers.ProductsManager

class AddProductFragment : Fragment() {

    var eteNombreProducto : EditText? = null
    var etePrecioProducto : EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        eteNombreProducto = view!!.findViewById(R.id.eteNombreProducto)
        etePrecioProducto = view!!.findViewById(R.id.etePrecioProducto)

        val butGuardarProducto = view!!.findViewById<Button>(R.id.butGuardarProducto)
        butGuardarProducto.setOnClickListener { _ : View ->
            val nombreProducto = eteNombreProducto!!.text.toString()
            val precioProducto = etePrecioProducto!!.text.toString()

            ProductsManager.getInstance().saveProduct(
                nombreProducto,
                precioProducto.toFloat()
            ) {
                Log.i("AddProductFragment", "Se guardo correctamente");
            }
        }
    }
}