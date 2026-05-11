package ec.edu.monster.ws.endpoints;

import ec.edu.monster.ws.modelos.RespuestaLogin;
import ec.edu.monster.ws.servicios.AutenticacionServicio;

/**
 * Endpoint de autenticación.
 * Recibe las solicitudes de login y delega al servicio.
 * @author BRYAN-PC
 */
public class AutenticacionEndpoint {

    private final AutenticacionServicio servicio;

    public AutenticacionEndpoint() {
        this.servicio = new AutenticacionServicio();
    }

    /**
     * Procesa una solicitud de autenticación.
     * @param usuario nombre de usuario
     * @param clave contraseña
     * @return resultado de la autenticación como String
     */
    public String autenticar(String usuario, String clave) {
        RespuestaLogin respuesta = servicio.autenticar(usuario, clave);
        if (respuesta.isExitoso()) {
            return "AUTENTICADO";
        } else {
            return "ERROR: " + respuesta.getMensaje();
        }
    }
}
