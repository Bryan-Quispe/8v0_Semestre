/*
 * Servicio Web SOAP - Conversor de Unidades
 * Grupo 04 - Arquitectura de Software
 */
package ec.edu.monster.ws;

import ec.edu.monster.ws.endpoints.AutenticacionEndpoint;
import ec.edu.monster.ws.endpoints.ConversionEndpoint;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * Fachada principal del servicio web SOAP.
 * Delega las operaciones a los endpoints correspondientes.
 * @author BRYAN-PC
 */
@WebService(serviceName = "WS")
public class WS {

    private final AutenticacionEndpoint autenticacionEndpoint;
    private final ConversionEndpoint conversionEndpoint;

    public WS() {
        this.autenticacionEndpoint = new AutenticacionEndpoint();
        this.conversionEndpoint = new ConversionEndpoint();
    }

    // =========================================================
    //  AUTENTICACIÓN
    // =========================================================

    /**
     * Autentica un usuario con credenciales en texto plano.
     * @param usuario nombre de usuario
     * @param clave contraseña
     * @return "AUTENTICADO" si es correcto, "ERROR: mensaje" si no
     */
    @WebMethod(operationName = "autenticar")
    public String autenticar(
            @WebParam(name = "usuario") String usuario,
            @WebParam(name = "clave") String clave) {
        return autenticacionEndpoint.autenticar(usuario, clave);
    }

    // =========================================================
    //  LONGITUD
    //  Unidades soportadas: m, km, cm, mm, in
    // =========================================================
    @WebMethod(operationName = "convertirLongitud")
    public double convertirLongitud(
            @WebParam(name = "valor") double valor,
            @WebParam(name = "desde") String desde,
            @WebParam(name = "hasta") String hasta) {
        return conversionEndpoint.convertirLongitud(valor, desde, hasta);
    }

    // =========================================================
    //  MASA
    //  Unidades soportadas: kg, g, mg, t, lb
    // =========================================================
    @WebMethod(operationName = "convertirMasa")
    public double convertirMasa(
            @WebParam(name = "valor") double valor,
            @WebParam(name = "desde") String desde,
            @WebParam(name = "hasta") String hasta) {
        return conversionEndpoint.convertirMasa(valor, desde, hasta);
    }

    // =========================================================
    //  TEMPERATURA
    //  Unidades soportadas: C, F, K, R (Rankine), Re (Réaumur)
    // =========================================================
    @WebMethod(operationName = "convertirTemperatura")
    public double convertirTemperatura(
            @WebParam(name = "valor") double valor,
            @WebParam(name = "desde") String desde,
            @WebParam(name = "hasta") String hasta) {
        return conversionEndpoint.convertirTemperatura(valor, desde, hasta);
    }

    // =========================================================
    //  AUXILIAR: listar unidades disponibles por categoría
    // =========================================================
    @WebMethod(operationName = "obtenerUnidades")
    public String obtenerUnidades(
            @WebParam(name = "categoria") String categoria) {
        return conversionEndpoint.obtenerUnidades(categoria);
    }
}