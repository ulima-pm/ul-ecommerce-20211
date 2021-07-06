package pe.edu.ulima.pm.ulecommerce

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.navigation.NavigationView
import com.google.gson.*
import pe.edu.ulima.pm.ulecommerce.fragments.AccountFragment
import pe.edu.ulima.pm.ulecommerce.fragments.AddProductFragment
import pe.edu.ulima.pm.ulecommerce.fragments.OnBottomBarMenuSelected
import pe.edu.ulima.pm.ulecommerce.fragments.ProductsFragment
import pe.edu.ulima.pm.ulecommerce.models.beans.User
import pe.edu.ulima.pm.ulecommerce.views.OnFaceClickListener
import pe.edu.ulima.pm.ulecommerce.views.ULFaceView

class MainActivity : AppCompatActivity(){
    var username : String? = null
    var dlaMain : DrawerLayout? = null
    var fragments : ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getLastUserSave()

        val toolbar = findViewById<Toolbar>(R.id.tbaMain)
        setSupportActionBar(toolbar)

        // Setear actionbar
        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.btn_star)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Crear fragments
        //username = intent.getStringExtra("USERNAME")
        username = getSharedPreferences(
            "USERS_DATA", Context.MODE_PRIVATE)
            .getString("USERNAME", null)

        val bundle : Bundle = Bundle()
        bundle.putString("USERNAME", username)

        fragments.add(AccountFragment())
        fragments.add(ProductsFragment())
        fragments.add(AddProductFragment())

        fragments[0].arguments = bundle // Seteamos parametros a AccountFragment

        // Configurar nuestra navegacion
        val nviMain = findViewById<NavigationView>(R.id.nviMain)
        dlaMain = findViewById(R.id.dlaMain)

        nviMain.setNavigationItemSelectedListener { item : MenuItem ->
            item.setChecked(true)
            val ft = supportFragmentManager.beginTransaction()
            if (item.itemId == R.id.mnuAccount) {
                // Abrimos fragment AccountFragment
                ft.replace(R.id.flaContent, fragments[0])
            }else if (item.itemId == R.id.mnuProducts) {
                // Abrimos fragment ProductsFragment
                ft.replace(R.id.flaContent, fragments[1])
            }
            ft.addToBackStack(null)
            ft.commit()
            dlaMain!!.closeDrawers()
            true
        }

        // Agregamos fragment por defecto
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.flaContent, fragments[0])
        ft.addToBackStack(null)
        ft.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            dlaMain!!.openDrawer(GravityCompat.START)
        }else if (item.itemId == R.id.mnuProductsActivity) {
            startActivity(Intent(this, ProductsActivity::class.java))
        }else if (item.itemId == R.id.mnuAddProduct) {
            // Cargamos el fragment AddProduct
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.flaContent, fragments[2])
            ft.addToBackStack(null)
            ft.commit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        dlaMain!!.closeDrawers()
    }

    private fun getLastUserSave() : User {
        var user : User? = null
        applicationContext.openFileInput("USERS_FILE.json").use {
            val reader = it.bufferedReader(Charsets.UTF_8)
            user = Gson().fromJson(reader.readLine(), User::class.java)

        }
        return user!!
    }
}