package ec.edu.monster.ws.cliente.movil.modelos

/**
 * Modelo de resultado de conversión.
 * @author BRYAN-PC
 */
data class ResultadoConversion(
    val valor: Double = 0.0,
    val desde: String = "",
    val hasta: String = "",
    val resultado: Double = 0.0,
    val exitoso: Boolean = false,
    val mensaje: String = ""
)
