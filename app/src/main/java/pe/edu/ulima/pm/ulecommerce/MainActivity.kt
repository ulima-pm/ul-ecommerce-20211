package pe.edu.ulima.pm.ulecommerce

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.*
import com.google.android.material.navigation.NavigationView
import com.google.gson.*
import pe.edu.ulima.pm.ulecommerce.fragments.*
import pe.edu.ulima.pm.ulecommerce.models.beans.User
import pe.edu.ulima.pm.ulecommerce.views.OnFaceClickListener
import pe.edu.ulima.pm.ulecommerce.views.ULFaceView

class MainActivity : AppCompatActivity(), TomarFotoListener{
    var username : String? = null
    var dlaMain : DrawerLayout? = null
    var fragments : ArrayList<Fragment> = ArrayList()
    lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    lateinit var locationCallback : LocationCallback
    var esPrimeraVezLocalizacion = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviderClient =LocationServices.getFusedLocationProviderClient(this)

        obtenerPermisosLocalizacion()

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

    override fun onPause() {
        super.onPause()
        pararLocalizacion()
    }

    override fun onResume() {
        super.onResume()
        if (!esPrimeraVezLocalizacion) {
            obtenerLocalizacionActual()
        }else {
            esPrimeraVezLocalizacion = false
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            // Regresamos de tomar la foto
            val bitmap : Bitmap = data!!.extras!!["data"] as Bitmap
            val fragmentAddProduct = fragments[2] as AddProductFragment
            fragmentAddProduct.cambiarImageView(bitmap)
        }
    }

    private fun getLastUserSave() : User {
        var user : User? = null
        applicationContext.openFileInput("USERS_FILE.json").use {
            val reader = it.bufferedReader(Charsets.UTF_8)
            user = Gson().fromJson(reader.readLine(), User::class.java)

        }
        return user!!
    }

    fun obtenerPermisosLocalizacion()  {
        val permission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (permission == PackageManager.PERMISSION_GRANTED) {
            // Caso que ya se tenian los permisos habilitados
            obtenerUltimaLocalizacion()
        } else {
            // No hay permisos habilitados
            val permissionWin = ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            if (permissionWin) {
                Toast.makeText(this, "Debe habilitar sus permisos", Toast.LENGTH_LONG).show()
            }else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    100
                )
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun obtenerUltimaLocalizacion() {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            Log.i("MainActivity", "${it.latitude} , ${it.longitude}")
        }
        obtenerLocalizacionActual()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUltimaLocalizacion()
            }else {
                // Caso que no me dan permiso
                finish()
            }

        }else if (requestCode == 200) {
            // Permisos de Camara
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(intent, 1000)
            }else {
                Log.e("MainActivity", "No dieron los permisos");
                finish();
            }
        }
    }

    fun createLocationRequest() : LocationRequest {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        return locationRequest
    }

    @SuppressLint("MissingPermission")
    fun obtenerLocalizacionActual() {
        val locationRequest = createLocationRequest()

        // Implementando clase abstracta LocationCallback
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(lr: LocationResult) {
                for (location in lr.locations) {
                    Log.i("MainActivity", "${location.latitude} , ${location.longitude}");
                }
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    fun pararLocalizacion() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    override fun tomarFoto() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(intent, 1000)
            }else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CAMERA), 200)
            }

        }
    }
}