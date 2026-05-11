package ec.edu.monster.ws.cliente.movil.actividades

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ec.edu.monster.ws.cliente.movil.R
import ec.edu.monster.ws.cliente.movil.servicios.SoapClienteServicio
import ec.edu.monster.ws.cliente.movil.utilidades.ConstantesApp
import kotlinx.coroutines.*

/**
 * Actividad de inicio de sesión.
 * @author BRYAN-PC
 */
class LoginActividad : AppCompatActivity() {

    private lateinit var txtUsuario: EditText
    private lateinit var txtClave: EditText
    private lateinit var btnLogin: Button
    private lateinit var lblError: TextView
    private lateinit var pbLoading: ProgressBar

    private val servicio = SoapClienteServicio()
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_login)

        txtUsuario = findViewById(R.id.txtUsuario)
        txtClave = findViewById(R.id.txtClave)
        btnLogin = findViewById(R.id.btnLogin)
        lblError = findViewById(R.id.lblError)
        pbLoading = findViewById(R.id.pbLoading)

        btnLogin.setOnClickListener { procesarLogin() }
    }

    private fun procesarLogin() {
        val usuario = txtUsuario.text.toString().trim()
        val clave = txtClave.text.toString().trim()

        if (usuario.isEmpty() || clave.isEmpty()) {
            lblError.text = "❌ Ingrese usuario y contraseña"
            lblError.visibility = android.view.View.VISIBLE
            return
        }

        btnLogin.isEnabled = false
        pbLoading.visibility = android.view.View.VISIBLE
        lblError.visibility = android.view.View.GONE

        scope.launch {
            try {
                val resultado = withContext(Dispatchers.IO) {
                    servicio.autenticar(usuario, clave)
                }

                if (resultado == "AUTENTICADO") {
                    val intent = Intent(this@LoginActividad, ConversionActividad::class.java)
                    intent.putExtra(ConstantesApp.EXTRA_USUARIO, usuario)
                    startActivity(intent)
                    finish()
                } else {
                    lblError.text = "❌ Credenciales incorrectas"
                    lblError.visibility = android.view.View.VISIBLE
                }
            } catch (e: Exception) {
                lblError.text = "❌ Error de conexión: ${e.message}"
                lblError.visibility = android.view.View.VISIBLE
            } finally {
                btnLogin.isEnabled = true
                pbLoading.visibility = android.view.View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
