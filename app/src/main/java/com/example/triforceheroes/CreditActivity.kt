package com.example.triforceheroes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreditActivity : AppCompatActivity() {
    private lateinit var userInfoText: TextView
    private lateinit var contactButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credit)

        // Inicializar las vistas usando findViewById
        userInfoText = findViewById(R.id.userInfoText)
        contactButton = findViewById(R.id.contactButton)

        // Recibir el nombre de usuario del Intent
        val username = intent.getStringExtra("USERNAME") ?: "Usuario"
        userInfoText.text = "$username, estás usando la aplicación Triforce Heroes. Si tienes algún problema, envíanos un correo usando el botón de abajo."

        // Configurar el Intent para enviar un correo
        contactButton.setOnClickListener {
            val email = "rodriguezdiazantonio04@gmail.com"
            val subject = getString(R.string.email_subject)

            // Intent para enviar correos electrónicos
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email") // URI válida para correo electrónico
                putExtra(Intent.EXTRA_SUBJECT, subject)
            }

            // Intent alternativo para mayor compatibilidad
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                // Intent alternativo con ACTION_SEND
                val fallbackIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                }
                if (fallbackIntent.resolveActivity(packageManager) != null) {
                    startActivity(Intent.createChooser(fallbackIntent, "Selecciona una aplicación de correo"))
                } else {
                    Toast.makeText(this, "No hay una aplicación de correo instalada.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}