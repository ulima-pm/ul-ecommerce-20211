package pe.edu.ulima.pm.ulecommerce.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.models.Product

class ProductsRVAdapter : RecyclerView.Adapter<ProductsRVAdapter.ViewHolder>
{
    class ViewHolder : RecyclerView.ViewHolder {
        var tviProductName : TextView? = null
        var tviProductPrice : TextView? = null

        constructor(view : View) : super(view) {

            tviProductName = view.findViewById(R.id.tviProductName)
            tviProductPrice = view.findViewById(R.id.tviProductPrice)
        }
    }

    private var products : ArrayList<Product>? = null

    constructor(products : ArrayList<Product>) : super(){
        this.products = products
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_products, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products!![position]

        holder.tviProductName!!.text = product.name
        holder.tviProductPrice!!.text = product.price.toString()
    }

    override fun getItemCount(): Int {
        return products!!.size;
    }
}