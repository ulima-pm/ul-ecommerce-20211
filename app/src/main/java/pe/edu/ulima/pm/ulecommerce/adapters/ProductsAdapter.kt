package pe.edu.ulima.pm.ulecommerce.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.models.Product

class ProductsAdapter :BaseAdapter{

    private var productList : ArrayList<Product>? = null
    private var inflater : LayoutInflater? = null

    constructor(context : Context, products : ArrayList<Product>) {
        productList = products
        inflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return productList!!.size
    }

    override fun getItem(position: Int): Any {
        return productList!![position]
    }

    override fun getItemId(position: Int): Long {
        return productList!![position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup?): View {
        val product = productList!![position]

        val view = inflater!!.inflate(R.layout.item_products, null)

        view.findViewById<TextView>(R.id.tviProductName).setText(product.name)
        view.findViewById<TextView>(R.id.tviProductPrice).setText(product.price.toString())

        return view
    }
}