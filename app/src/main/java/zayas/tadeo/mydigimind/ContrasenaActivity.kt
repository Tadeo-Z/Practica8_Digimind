package zayas.tadeo.mydigimind

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ContrasenaActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contrasena)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = Firebase.auth

        val btn_restablecer: Button = findViewById(R.id.btn_restablecer)

        btn_restablecer.setOnClickListener{
            val et_correo: EditText = findViewById(R.id.et_correo_cont)

            var correo: String = et_correo.text.toString()

            if(!correo.isNullOrBlank()){

                auth.sendPasswordResetEmail(correo)
                    .addOnCompleteListener{ task ->

                        if (task.isSuccessful){
                            Toast.makeText(this, "Se envió un correo a $correo",
                                Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "Error al enviar correo",
                                Toast.LENGTH_SHORT).show()
                        }

                    }

            }else{
                Toast.makeText(this,"Ingresar correo",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}