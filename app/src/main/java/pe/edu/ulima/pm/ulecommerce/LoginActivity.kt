package pe.edu.ulima.pm.ulecommerce

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pe.edu.ulima.pm.ulecommerce.views.OnFaceClickListener

class LoginActivity : AppCompatActivity(), View.OnClickListener{
    var eteUsuario : EditText? = null
    var etePassword : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("username", eteUsuario?.text.toString())
    }

    override fun onClick(v: View?) {
        val usuario = eteUsuario!!.text.toString() //[LoginActivity : Controller] -> [EditText : View]
        val password = etePassword!!.text.toString()

        if (usuario == "pm" && password == "123") {
            // Login correcto
            val intent : Intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            intent.putExtra("USERNAME", usuario)
            startActivity(intent)

        }else {
            // Login incorrecto
            Toast.makeText(this, "Error Login", Toast.LENGTH_SHORT).show()
        }
    }
}