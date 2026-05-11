package ec.edu.monster.ws.cliente.consola.modelos;

/**
 * Modelo que representa la sesión del usuario autenticado.
 * @author BRYAN-PC
 */
public class SesionUsuario {

    private String usuario;
    private boolean autenticado;

    public SesionUsuario() {
        this.autenticado = false;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
}
