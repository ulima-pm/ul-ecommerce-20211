package pe.edu.ulima.pm.ulecommerce.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ulecommerce.R

class AccountFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflar el XML
        return inflater.inflate(
                R.layout.fragment_account,
                container,
                false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val username = arguments!!.getString("USERNAME")

        val tviSaludo = view!!.findViewById<TextView>(R.id.tviSaludo)
        tviSaludo.text = "Hola: ${username}"
    }
}
