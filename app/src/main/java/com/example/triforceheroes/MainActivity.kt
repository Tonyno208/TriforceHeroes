package com.example.triforceheroes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var navigateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar las vistas usando findViewById
        usernameInput = findViewById(R.id.usernameInput)
        navigateButton = findViewById(R.id.navigateButton)

        // Configurar el evento para el botón de navegación
        navigateButton.setOnClickListener {
            val username = usernameInput.text?.toString()?.trim() ?: ""

            if (username.isNotEmpty()) {
                val intent = Intent(this, CreditActivity::class.java).apply {
                    putExtra("USERNAME", username)
                }
                startActivity(intent)
            } else {
                // Mostrar error si el campo de texto está vacío
                usernameInput.error = "Por favor, introduce un nombre de usuario."
            }
        }
    }
}
