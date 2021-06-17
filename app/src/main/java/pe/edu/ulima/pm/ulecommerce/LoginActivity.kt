package pe.edu.ulima.pm.ulecommerce

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import pe.edu.ulima.pm.ulecommerce.models.managers.UsersManager
import pe.edu.ulima.pm.ulecommerce.models.persistence.AppDatabase
import pe.edu.ulima.pm.ulecommerce.views.OnFaceClickListener

class LoginActivity : AppCompatActivity(), View.OnClickListener{
    private var eteUsuario : EditText? = null
    private var etePassword : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (checkIfLogin()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val butLogin : Button = findViewById(R.id.butLogin)
        val butSignup = findViewById<Button>(R.id.butRegistrar)

        eteUsuario = findViewById<EditText>(R.id.eteUsuario)
        etePassword = findViewById<EditText>(R.id.etePassword)

        if (savedInstanceState != null) {
            val username = savedInstanceState.getString("username")
            eteUsuario?.setText(username)
        }

        butLogin.setOnClickListener(this)// [LoginActivity : Controller] -> [Button : View]
        butSignup.setOnClickListener { _: View ->
            val intent = Intent(this, SignupActivity::class.java)
            startActivityForResult(intent, 100)
        }

        displayUsersTest() // Test
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // Se grabo correctamente
            val username = data!!.getStringExtra("USERNAME")
            val password = data!!.getStringExtra("PASSWORD")

            eteUsuario?.setText(username)
            etePassword?.setText(password)
        } else if (resultCode == Activity.RESULT_CANCELED) {
            // Se cancelo y no se registro un nuevo usuario
        }
        displayUsersTest() // Test
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("username", eteUsuario?.text.toString())
    }

    override fun onClick(v: View?) {
        val usuario = eteUsuario!!.text.toString() //[LoginActivity : Controller] -> [EditText : View]
        val password = etePassword!!.text.toString()

        UsersManager.getInstance().login({ username : String, name : String->
            val intent : Intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            //intent.putExtra("USERNAME", username)

            val spEditor = getSharedPreferences("USERS_DATA", Context.MODE_PRIVATE).edit()
            spEditor.putString("USERNAME", username)
            spEditor.putString("NAME", name)
            spEditor.apply()

            startActivity(intent)
        },{ error : String->
            Log.e("LoginActivity", error)
            Toast.makeText(this, "Error Login", Toast.LENGTH_SHORT).show()
        }, usuario, password)
    }

    private fun checkIfLogin() : Boolean{
        val username = getSharedPreferences("USERS_DATA", Context.MODE_PRIVATE)
            .getString("USERNAME", null)
        if (username == null) return false
        return true
    }

    private fun displayUsersTest() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "ULECOMMERCE_DB"
        ).fallbackToDestructiveMigration().build()
        val hilo = Thread {

            val listaUsuarios = db.userDAO().findAll()
            Log.i("LoginActivity", listaUsuarios.size.toString());
            listaUsuarios.forEach { user ->
                Log.i("LoginActivity", user.username)
            }
        }
        hilo.start()

    }
}