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

class registro : AppCompatActivity() {

        private val url = "http://192.168.18.28/ProgramacionMovil_II/appmobile/registro.php"

        private lateinit var btnRegistrarseR: Button
        private lateinit var txtUsuarioR: EditText
        private lateinit var txtContrasenaR: EditText
        private lateinit var txtNombre: EditText
        private lateinit var txtApellido: EditText
        private lateinit var txtDocumento: EditText



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_registro)

            btnRegistrarseR = findViewById(R.id.bntregistrarseR)
            txtUsuarioR = findViewById(R.id.txtUsuarioR)
            txtContrasenaR = findViewById(R.id.txtContrasenaR)
            txtNombre= findViewById(R.id.txtNombre)
            txtApellido = findViewById(R.id.txtApellido)
            txtDocumento = findViewById(R.id.txtDocumento)


            btnRegistrarseR.setOnClickListener {
                this.validarUsuario(this.url)
            }
        }

        private fun validarUsuario(url: String) {
                val stringRequest = object : StringRequest(Method.POST, url,
                Response.Listener { response ->
                    if (response.isEmpty()) {
                        Toast.makeText(this, "usuario o contraseña incorrectos.", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "ingreso exitoso.", Toast.LENGTH_LONG).show()

                    }
                }, Response.ErrorListener { error ->
                    Toast.makeText(this, "Error con valley: " + error.toString(), Toast.LENGTH_LONG).show()
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val parametros: MutableMap<String, String> = HashMap()
                    parametros["user"] = txtUsuarioR.text.toString()
                    parametros["password"] = txtContrasenaR.text.toString()
                    parametros["name"] = txtNombre.text.toString()
                    parametros["lastname"] = txtApellido.text.toString()
                    parametros["document"] = txtDocumento.text.toString()

                    return parametros
                }
            }

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(stringRequest)
        }

        }




