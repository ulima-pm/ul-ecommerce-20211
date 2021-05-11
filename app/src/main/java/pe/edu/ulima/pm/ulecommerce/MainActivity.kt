package pe.edu.ulima.pm.ulecommerce

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import pe.edu.ulima.pm.ulecommerce.fragments.AccountFragment
import pe.edu.ulima.pm.ulecommerce.fragments.OnBottomBarMenuSelected
import pe.edu.ulima.pm.ulecommerce.fragments.ProductsFragment
import pe.edu.ulima.pm.ulecommerce.views.OnFaceClickListener
import pe.edu.ulima.pm.ulecommerce.views.ULFaceView

class MainActivity : AppCompatActivity(), OnBottomBarMenuSelected{
    var username : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = intent.getStringExtra("USERNAME")

        val bundle : Bundle = Bundle()
        bundle.putString("USERNAME", username)

        val fragment = AccountFragment()
        fragment.arguments = bundle // seteamos argumentos al fragment

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent, fragment)
        ft.addToBackStack(null)
        ft.commit()

    }

    override fun onMenuSelected(menuType: String) {
        var fragment : Fragment? = null
        if (menuType == "Account") {
            val bundle : Bundle = Bundle()
            bundle.putString("USERNAME", username)
            fragment = AccountFragment()
            fragment.arguments = bundle
        }else if (menuType == "Products") {
            fragment = ProductsFragment()
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.flaContent, fragment)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

//    override fun onClick(view : ULFaceView) {
//        Log.i("MainActivity", "Se hizo click")
//        view.altoBoca = view.altoBoca?.plus(100f)
//    }

}