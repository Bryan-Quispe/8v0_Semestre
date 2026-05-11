package ec.edu.monster.ws.cliente.movil.actividades

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ec.edu.monster.ws.cliente.movil.R
import ec.edu.monster.ws.cliente.movil.servicios.SoapClienteServicio
import ec.edu.monster.ws.cliente.movil.utilidades.ConstantesApp
import kotlinx.coroutines.*

/**
 * Actividad de conversión de unidades.
 * @author BRYAN-PC
 */
class ConversionActividad : AppCompatActivity() {

    private lateinit var lblUsuario: TextView
    private lateinit var spnCategoria: Spinner
    private lateinit var txtValor: EditText
    private lateinit var spnDesde: Spinner
    private lateinit var spnHasta: Spinner
    private lateinit var btnConvertir: Button
    private lateinit var btnSalir: Button
    private lateinit var lblResultado: TextView
    private lateinit var lblError: TextView

    private val servicio = SoapClienteServicio()
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_conversion)

        val usuario = intent.getStringExtra(ConstantesApp.EXTRA_USUARIO) ?: "Usuario"

        lblUsuario = findViewById(R.id.lblUsuario)
        spnCategoria = findViewById(R.id.spnCategoria)
        txtValor = findViewById(R.id.txtValor)
        spnDesde = findViewById(R.id.spnDesde)
        spnHasta = findViewById(R.id.spnHasta)
        btnConvertir = findViewById(R.id.btnConvertir)
        btnSalir = findViewById(R.id.btnSalir)
        lblResultado = findViewById(R.id.lblResultado)
        lblError = findViewById(R.id.lblError)

        lblUsuario.text = "👤 $usuario"

        // Configurar spinner de categorías
        val categorias = arrayOf("longitud", "masa", "temperatura")
        val adapterCat = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias)
        spnCategoria.adapter = adapterCat

        spnCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                actualizarUnidades(categorias[pos])
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        btnConvertir.setOnClickListener { procesarConversion() }
        btnSalir.setOnClickListener { finish() }
    }

    private fun actualizarUnidades(categoria: String) {
        val unidades = when (categoria) {
            "longitud" -> arrayOf("m", "km", "cm", "mm", "in")
            "masa" -> arrayOf("kg", "g", "mg", "t", "lb")
            "temperatura" -> arrayOf("C", "F", "K", "R", "Re")
            else -> arrayOf()
        }
        val adapterUni = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, unidades)
        spnDesde.adapter = adapterUni
        spnHasta.adapter = adapterUni
    }

    private fun procesarConversion() {
        lblError.visibility = View.GONE
        lblResultado.visibility = View.GONE

        val categoria = spnCategoria.selectedItem?.toString() ?: ""
        val valorStr = txtValor.text.toString().trim()
        val desde = spnDesde.selectedItem?.toString() ?: ""
        val hasta = spnHasta.selectedItem?.toString() ?: ""

        if (valorStr.isEmpty() || desde.isEmpty() || hasta.isEmpty()) {
            mostrarError("Complete todos los campos")
            return
        }

        val valor: Double
        try {
            valor = valorStr.toDouble()
        } catch (e: NumberFormatException) {
            mostrarError("Ingrese un valor numérico válido")
            return
        }

        btnConvertir.isEnabled = false

        scope.launch {
            try {
                val resultado = withContext(Dispatchers.IO) {
                    when (categoria) {
                        "longitud" -> servicio.convertirLongitud(valor, desde, hasta)
                        "masa" -> servicio.convertirMasa(valor, desde, hasta)
                        "temperatura" -> servicio.convertirTemperatura(valor, desde, hasta)
                        else -> throw Exception("Categoría no válida")
                    }
                }

                val formato = java.text.DecimalFormat("#.######")
                val valorFmt = formato.format(valor)
                val resultadoFmt = formato.format(resultado)

                lblResultado.text = "📊 $valorFmt $desde = $resultadoFmt $hasta"
                lblResultado.visibility = View.VISIBLE

            } catch (e: Exception) {
                mostrarError("Error: ${e.message}")
            } finally {
                btnConvertir.isEnabled = true
            }
        }
    }

    private fun mostrarError(mensaje: String) {
        lblError.text = "❌ $mensaje"
        lblError.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}
