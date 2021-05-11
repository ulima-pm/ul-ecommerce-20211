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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = AccountFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent, fragment)
        ft.commit()


//        val faceView = findViewById<ULFaceView>(R.id.faceView)
//        faceView.setOnFaceClickListener(this)
//
//        val username = intent.getStringExtra("USERNAME")
//        Log.i("MainActivity", "USERNAME: ${username}")
    }

    override fun onMenuSelected(menuType: String) {
        var fragment : Fragment? = null
        if (menuType == "Account") {
            fragment = AccountFragment()
        }else if (menuType == "Products") {
            fragment = ProductsFragment()
        }

        if (fragment != null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.flaContent, fragment)
            ft.commit()
        }
    }

//    override fun onClick(view : ULFaceView) {
//        Log.i("MainActivity", "Se hizo click")
//        view.altoBoca = view.altoBoca?.plus(100f)
//    }

}