package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication2.databinding.ActivityIndexBinding

class index : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        val btncancelarI = findViewById<Button>(R.id.btnCancelarI)
        btncancelarI.setOnClickListener {
            val intento = Intent(this, MainActivity::class.java)
            startActivity(intento)
        }
        // Configuración del botón "ubicacion"
        val btnmapa = findViewById<Button>(R.id.btnmapa)
        btnmapa.setOnClickListener {
            val intent = Intent(this, maps::class.java)
            startActivity(intent)
        }
    }


    override fun onBackPressed() {
    }
}
