package ec.edu.monster.ws.cliente.movil.modelos

/**
 * Modelo de usuario para la sesión.
 * @author BRYAN-PC
 */
data class Usuario(
    val nombreUsuario: String = "",
    val autenticado: Boolean = false
)
