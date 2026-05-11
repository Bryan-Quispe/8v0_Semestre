package ec.edu.monster.ws.cliente.movil.servicios

import ec.edu.monster.ws.cliente.movil.utilidades.ConstantesApp
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 * Servicio SOAP cliente para Android.
 * Construye y envía peticiones SOAP XML manualmente.
 * @author BRYAN-PC
 */
class SoapClienteServicio {

    private val urlServicio = ConstantesApp.URL_SERVICIO
    private val namespace = ConstantesApp.NAMESPACE

    /**
     * Autentica al usuario contra el servicio SOAP.
     */
    fun autenticar(usuario: String, clave: String): String {
        val cuerpo = """
            <ws:autenticar>
                <usuario>$usuario</usuario>
                <clave>$clave</clave>
            </ws:autenticar>
        """.trimIndent()

        val respuesta = enviarPeticion(construirEnvelope(cuerpo))
        return extraerValor(respuesta, "return")
    }

    /**
     * Convierte longitud.
     */
    fun convertirLongitud(valor: Double, desde: String, hasta: String): Double {
        return convertir("convertirLongitud", valor, desde, hasta)
    }

    /**
     * Convierte masa.
     */
    fun convertirMasa(valor: Double, desde: String, hasta: String): Double {
        return convertir("convertirMasa", valor, desde, hasta)
    }

    /**
     * Convierte temperatura.
     */
    fun convertirTemperatura(valor: Double, desde: String, hasta: String): Double {
        return convertir("convertirTemperatura", valor, desde, hasta)
    }

    /**
     * Obtiene las unidades disponibles.
     */
    fun obtenerUnidades(categoria: String): String {
        val cuerpo = """
            <ws:obtenerUnidades>
                <categoria>$categoria</categoria>
            </ws:obtenerUnidades>
        """.trimIndent()

        val respuesta = enviarPeticion(construirEnvelope(cuerpo))
        return extraerValor(respuesta, "return")
    }

    // ========== Métodos privados ==========

    private fun convertir(operacion: String, valor: Double, desde: String, hasta: String): Double {
        val cuerpo = """
            <ws:$operacion>
                <valor>$valor</valor>
                <desde>$desde</desde>
                <hasta>$hasta</hasta>
            </ws:$operacion>
        """.trimIndent()

        val respuesta = enviarPeticion(construirEnvelope(cuerpo))
        return extraerValor(respuesta, "return").toDouble()
    }

    private fun construirEnvelope(cuerpo: String): String {
        return """<?xml version="1.0" encoding="UTF-8"?>
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:ws="$namespace">
                <soapenv:Body>$cuerpo</soapenv:Body>
            </soapenv:Envelope>""".trimIndent()
    }

    private fun enviarPeticion(soapXml: String): String {
        val url = URL(urlServicio)
        val conexion = url.openConnection() as HttpURLConnection
        conexion.requestMethod = "POST"
        conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8")
        conexion.doOutput = true
        conexion.connectTimeout = 5000
        conexion.readTimeout = 5000

        conexion.outputStream.use { os ->
            os.write(soapXml.toByteArray(Charsets.UTF_8))
            os.flush()
        }

        val codigo = conexion.responseCode
        val inputStream = if (codigo in 200..299) conexion.inputStream else conexion.errorStream

        val respuesta = inputStream.bufferedReader(Charsets.UTF_8).use { it.readText() }
        conexion.disconnect()
        return respuesta
    }

    private fun extraerValor(xml: String, etiqueta: String): String {
        val patrones = arrayOf("<$etiqueta>", "<ns2:$etiqueta>", "<return>")
        val cierres = arrayOf("</$etiqueta>", "</ns2:$etiqueta>", "</return>")

        for (i in patrones.indices) {
            val inicio = xml.indexOf(patrones[i])
            if (inicio != -1) {
                val inicioValor = inicio + patrones[i].length
                val fin = xml.indexOf(cierres[i], inicioValor)
                if (fin != -1) return xml.substring(inicioValor, fin)
            }
        }
        return ""
    }
}
