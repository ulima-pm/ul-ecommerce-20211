package pe.edu.ulima.pm.ulecommerce.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import pe.edu.ulima.pm.ulecommerce.fragments.SingleProductFragment
import pe.edu.ulima.pm.ulecommerce.models.beans.Product

class ProductsSliderAdapter : FragmentStatePagerAdapter {
    var productList : ArrayList<Product>? = null
    var fragmentList : ArrayList<Fragment>? = null

    constructor(fm : FragmentManager, products : ArrayList<Product>)
            : super(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        productList = products

        // Creamos arraylist de fragments con su data de producto
        fragmentList = ArrayList()
        for (product in productList!!) {
            fragmentList!!.add(SingleProductFragment(product))
        }
    }

    override fun getCount(): Int {
        return productList?.size ?: 0
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }
}

//class ProductsSliderAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){
//    override fun getCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItem(position: Int): Fragment {
//        TODO("Not yet implemented")
//    }
//
//}