package ec.edu.monster.ws.endpoints;

import ec.edu.monster.ws.modelos.SolicitudConversion;
import ec.edu.monster.ws.modelos.RespuestaConversion;
import ec.edu.monster.ws.servicios.ConversionServicio;

/**
 * Endpoint de conversión de unidades.
 * Recibe las solicitudes de conversión y delega al servicio.
 * @author BRYAN-PC
 */
public class ConversionEndpoint {

    private final ConversionServicio servicio;

    public ConversionEndpoint() {
        this.servicio = new ConversionServicio();
    }

    /**
     * Convierte longitud.
     */
    public double convertirLongitud(double valor, String desde, String hasta) {
        SolicitudConversion sol = new SolicitudConversion("longitud", valor, desde, hasta);
        RespuestaConversion resp = servicio.convertir(sol);
        return resp.getResultado();
    }

    /**
     * Convierte masa.
     */
    public double convertirMasa(double valor, String desde, String hasta) {
        SolicitudConversion sol = new SolicitudConversion("masa", valor, desde, hasta);
        RespuestaConversion resp = servicio.convertir(sol);
        return resp.getResultado();
    }

    /**
     * Convierte temperatura.
     */
    public double convertirTemperatura(double valor, String desde, String hasta) {
        SolicitudConversion sol = new SolicitudConversion("temperatura", valor, desde, hasta);
        RespuestaConversion resp = servicio.convertir(sol);
        return resp.getResultado();
    }

    /**
     * Obtiene las unidades disponibles.
     */
    public String obtenerUnidades(String categoria) {
        return servicio.obtenerUnidades(categoria);
    }
}
