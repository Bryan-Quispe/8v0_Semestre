package ec.edu.monster.ws.cliente.web.modelos;

/**
 * Modelo de formulario de login para Thymeleaf.
 * @author BRYAN-PC
 */
public class LoginFormulario {

    private String usuario;
    private String clave;

    public LoginFormulario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
