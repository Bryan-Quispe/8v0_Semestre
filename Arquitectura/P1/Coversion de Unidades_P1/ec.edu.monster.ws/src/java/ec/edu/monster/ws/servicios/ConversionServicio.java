package ec.edu.monster.ws.servicios;

import ec.edu.monster.ws.modelos.RespuestaConversion;
import ec.edu.monster.ws.modelos.SolicitudConversion;
import ec.edu.monster.ws.utilidades.ConversorUtil;

/**
 * Servicio de conversión de unidades.
 * Delega la lógica de cálculo a ConversorUtil.
 * @author BRYAN-PC
 */
public class ConversionServicio {

    /**
     * Realiza una conversión de unidades según la categoría.
     * @param solicitud datos de la conversión
     * @return RespuestaConversion con el resultado
     */
    public RespuestaConversion convertir(SolicitudConversion solicitud) {
        try {
            double resultado;
            switch (solicitud.getCategoria().toLowerCase()) {
                case "longitud":
                    resultado = ConversorUtil.convertirLongitud(
                            solicitud.getValor(), solicitud.getDesde(), solicitud.getHasta());
                    break;
                case "masa":
                    resultado = ConversorUtil.convertirMasa(
                            solicitud.getValor(), solicitud.getDesde(), solicitud.getHasta());
                    break;
                case "temperatura":
                    resultado = ConversorUtil.convertirTemperatura(
                            solicitud.getValor(), solicitud.getDesde(), solicitud.getHasta());
                    break;
                default:
                    return new RespuestaConversion(false, 0,
                            "Categoría no válida. Use: longitud, masa, temperatura");
            }
            String msg = String.format("%.4f %s = %.4f %s",
                    solicitud.getValor(), solicitud.getDesde(),
                    resultado, solicitud.getHasta());
            return new RespuestaConversion(true, resultado, msg);
        } catch (Exception e) {
            return new RespuestaConversion(false, 0, "Error en conversión: " + e.getMessage());
        }
    }

    /**
     * Obtiene las unidades disponibles para una categoría.
     * @param categoria la categoría de unidades
     * @return cadena con las unidades separadas por coma
     */
    public String obtenerUnidades(String categoria) {
        return ConversorUtil.obtenerUnidades(categoria);
    }
}
