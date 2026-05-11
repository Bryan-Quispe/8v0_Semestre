package ec.edu.monster.ws.modelos;

/**
 * Modelo de respuesta para el proceso de autenticación.
 * @author BRYAN-PC
 */
public class RespuestaLogin {

    private boolean exitoso;
    private String mensaje;

    public RespuestaLogin() {
    }

    public RespuestaLogin(boolean exitoso, String mensaje) {
        this.exitoso = exitoso;
        this.mensaje = mensaje;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
