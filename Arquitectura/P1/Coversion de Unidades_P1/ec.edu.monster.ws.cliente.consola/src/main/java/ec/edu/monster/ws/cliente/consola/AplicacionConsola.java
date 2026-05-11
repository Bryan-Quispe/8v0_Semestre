package ec.edu.monster.ws.cliente.consola;

import ec.edu.monster.ws.cliente.consola.controladores.MenuControlador;
import ec.edu.monster.ws.cliente.consola.servicios.SoapClienteServicio;

/**
 * Punto de entrada principal del cliente de consola.
 * Consume el servicio SOAP de conversión de unidades.
 * @author BRYAN-PC
 */
public class AplicacionConsola {

    // URL del servicio SOAP desplegado en GlassFish
    private static final String URL_SERVICIO = "http://localhost:8080/ec.edu.monster.ws/WS";

    public static void main(String[] args) {
        String url = URL_SERVICIO;

        // Permitir URL personalizada por argumento
        if (args.length > 0) {
            url = args[0];
        }

        SoapClienteServicio servicio = new SoapClienteServicio(url);
        MenuControlador controlador = new MenuControlador(servicio);
        controlador.iniciar();
    }
}
