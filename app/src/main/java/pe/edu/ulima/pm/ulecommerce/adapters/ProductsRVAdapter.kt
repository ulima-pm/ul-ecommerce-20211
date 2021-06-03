package pe.edu.ulima.pm.ulecommerce.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.ulima.pm.ulecommerce.R
import pe.edu.ulima.pm.ulecommerce.models.beans.Product

interface OnProductItemClickListener {
    fun onClick(product : Product)
}

class ProductsRVAdapter : RecyclerView.Adapter<ProductsRVAdapter.ViewHolder>
{
    class ViewHolder : RecyclerView.ViewHolder {
        var tviProductName : TextView? = null
        var tviProductPrice : TextView? = null
        var iviProductImage : ImageView? = null

        constructor(view : View) : super(view) {
            iviProductImage = view.findViewById(R.id.iviProductImage)
            tviProductName = view.findViewById(R.id.tviProductName)
            tviProductPrice = view.findViewById(R.id.tviProductPrice)
        }
    }

    private var products : ArrayList<Product>? = null
    private var listener : OnProductItemClickListener? = null
    private var context : Context? = null

    constructor(products : ArrayList<Product>,
                listener : OnProductItemClickListener,
                context : Context) : super(){
        this.products = products
        this.listener = listener
        this.context = context
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

        //Picasso.get().load(product.image).into(holder.iviProductImage)
        Glide.with(context!!).load(product.image!!)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.iviProductImage!!)

        holder.itemView.setOnClickListener { v : View ->
            listener!!.onClick(products!![position])
        }
    }

    override fun getItemCount(): Int {
        return products!!.size;
    }
}