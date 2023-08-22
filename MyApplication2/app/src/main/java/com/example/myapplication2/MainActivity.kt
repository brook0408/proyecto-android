package com.example.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {

    // URL del script PHP para el inicio de sesión
    private val url = "http://192.168.18.28/ProgramacionMovil_II/appmobile/iniciarsesion.php"

    private lateinit var btnEntrar: Button
    private lateinit var txtUsuario: EditText
    private lateinit var txtContrasena: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de variables
        btnEntrar = findViewById(R.id.btnEntrar)
        txtUsuario = findViewById(R.id.txtUsuario)
        txtContrasena = findViewById(R.id.txtContrasena)

        // Configuración del botón "Entrar"
        btnEntrar.setOnClickListener {
            this.validarUsuario(this.url) // Llamar a la función para validar el usuario
        }

        // Configuración del botón "Registrarse"
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)
        btnRegistrarse.setOnClickListener {
            val intent = Intent(this, registro::class.java)
            startActivity(intent)
        }
    }
    private fun validarUsuario(url: String) {
        // Crear una solicitud POST utilizando Volley
        val stringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                if (response.isEmpty()) {
                    Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Ingreso exitoso.", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Error con Volley: " + error.toString(), Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                // Parámetros para enviar al script PHP (usuario y contraseña)
                val parametros: MutableMap<String, String> = HashMap()
                parametros["usuario"] = txtUsuario.text.toString() // Obtener el usuario del campo de texto
                parametros["contraseña"] = txtContrasena.text.toString() // Obtener la contraseña del campo de texto
                return parametros // Devolver los parámetros
            }
        }

        // Agregar la solicitud a la cola de peticiones de Volley
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
