package ec.edu.monster.ws.cliente.escritorio.modelos;

/**
 * Modelo de datos para login en JavaFX.
 * @author BRYAN-PC
 */
public class LoginModelo {

    private String usuario;
    private String clave;
    private boolean autenticado;

    public LoginModelo() {
        this.autenticado = false;
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public boolean isAutenticado() { return autenticado; }
    public void setAutenticado(boolean autenticado) { this.autenticado = autenticado; }
}
