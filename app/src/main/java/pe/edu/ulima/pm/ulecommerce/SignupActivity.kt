package pe.edu.ulima.pm.ulecommerce

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import com.google.gson.Gson
import pe.edu.ulima.pm.ulecommerce.models.beans.User
import pe.edu.ulima.pm.ulecommerce.models.persistence.AppDatabase
import java.nio.charset.Charset

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
            saveLocalUserRoom()


        }

        butCancel.setOnClickListener { _ : View ->
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }

    private fun saveLocalUserRoom() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "ULECOMMERCE_DB"
        ).build()
        val userDAO = db.userDAO()
        val userEntity = pe.edu.ulima.pm.ulecommerce.models.persistence.entities.User(
            0,
            "",
            eteUser!!.text.toString(),
            etePassword!!.text.toString(),
        )
        val hilo = Thread {
            userDAO.insert(userEntity)

            runOnUiThread {
                val intent = Intent()
                intent.putExtra("USERNAME", eteUser!!.text.toString())
                intent.putExtra("PASSWORD", etePassword!!.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }.start()

    }

    private fun saveLocalUser() {
        val user = User(eteUser!!.text.toString(), etePassword!!.text.toString())
        val jsUser = Gson().toJson(user)

        applicationContext.openFileOutput("USERS_FILE.json", Context.MODE_PRIVATE).use {
            it.write(jsUser.toByteArray(Charset.forName("utf-8")))
        }
    }
}