package pe.edu.ulima.pm.ulecommerce.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulecommerce.MainActivity
import pe.edu.ulima.pm.ulecommerce.R

interface OnBottomBarMenuSelected {
    fun onMenuSelected(menuType : String)
}

class BottomBarFragment  : Fragment(){
    var butAccount : Button? = null
    var butProducts : Button? = null
    var listener : OnBottomBarMenuSelected? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnBottomBarMenuSelected
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(
                R.layout.fragment_bottombar,
                container,
                false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        butAccount = view!!.findViewById(R.id.butAccount)
        butProducts = view!!.findViewById<Button>(R.id.butProducts)

        butAccount!!.setOnClickListener { _ : View ->
            listener!!.onMenuSelected("Account")
        }

        butProducts!!.setOnClickListener { _ : View ->
            listener!!.onMenuSelected("Products")
        }
    }
}