package pe.edu.ulima.pm.ulecommerce.fragments

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.models.managers.ProductsManager

interface  TomarFotoListener {
    fun tomarFoto()
}

class AddProductFragment : Fragment() {

    var eteNombreProducto : EditText? = null
    var etePrecioProducto : EditText? = null
    lateinit var tomarFotoListener : TomarFotoListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        tomarFotoListener = context as TomarFotoListener
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
                Toast.makeText(activity, "Se agregó correctamente", Toast.LENGTH_SHORT).show()
            }
        }

        val butTomarFoto = view!!.findViewById<Button>(R.id.butTomarFoto);
        butTomarFoto.setOnClickListener {
            Log.i("AddProductFragment", "butTomarFoto")
            tomarFotoListener.tomarFoto()
        }
    }

    fun cambiarImageView(bitmap : Bitmap) {
        val iviFotoProducto = activity!!.findViewById<ImageView>(R.id.iviFotoProducto)
        iviFotoProducto.setImageBitmap(bitmap)
    }
}