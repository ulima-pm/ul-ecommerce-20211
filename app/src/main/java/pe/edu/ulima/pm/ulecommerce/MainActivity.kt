package pe.edu.ulima.pm.ulecommerce

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import pe.edu.ulima.pm.ulecommerce.views.OnFaceClickListener
import pe.edu.ulima.pm.ulecommerce.views.ULFaceView

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        val faceView = findViewById<ULFaceView>(R.id.faceView)
//        faceView.setOnFaceClickListener(this)
//
//        val username = intent.getStringExtra("USERNAME")
//        Log.i("MainActivity", "USERNAME: ${username}")
    }

//    override fun onClick(view : ULFaceView) {
//        Log.i("MainActivity", "Se hizo click")
//        view.altoBoca = view.altoBoca?.plus(100f)
//    }
}