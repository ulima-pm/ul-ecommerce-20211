package pe.edu.ulima.pm.ulecommerce.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.w3c.dom.Text
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.models.Product

class ProductsAdapter :BaseAdapter{

    class ViewHolder {
        var tviProductName : TextView? = null
        var tviProductPrice : TextView? = null

        constructor(view : View) {
            tviProductName = view.findViewById(R.id.tviProductName)
            tviProductPrice = view.findViewById(R.id.tviProductPrice)
        }
    }

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
        // Reciclado de vistas
        val product = productList!![position]

        var view : View? = null
        var viewHolder : ViewHolder? = null

        if (convertView == null) {
            // Primera vez que se llama este metodo para determinada position
            view = inflater!!.inflate(R.layout.item_products, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else {
            view = convertView
            viewHolder = view.tag as ViewHolder?
        }


        viewHolder!!.tviProductName!!.setText(product.name)
        viewHolder!!.tviProductPrice!!.setText(product.price.toString())

        return view!!
    }
}