package pe.edu.ulima.pm.ulecommerce

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class SignupActivity : AppCompatActivity() {
    var eteUser : EditText? = null
    var etePassword : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        eteUser = findViewById(R.id.eteUsername)
        etePassword = findViewById(R.id.etePassword)

        val butSave = findViewById<Button>(R.id.butSave)
        val butCancel = findViewById<Button>(R.id.butCancel)

        butSave.setOnClickListener{ _ : View ->
            // TODO: Codigo para grabar un nuevo usuario
            val intent = Intent()
            intent.putExtra("USERNAME", eteUser!!.text.toString())
            intent.putExtra("PASSWORD", etePassword!!.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        butCancel.setOnClickListener { _ : View ->
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}