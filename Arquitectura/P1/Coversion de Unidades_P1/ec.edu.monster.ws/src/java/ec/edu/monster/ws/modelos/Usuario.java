package ec.edu.monster.ws.modelos;

/**
 * Modelo que representa un usuario del sistema.
 * Autenticación en texto plano, sin base de datos.
 * @author BRYAN-PC
 */
public class Usuario {

    private String usuario;
    private String clave;
    private String nombreCompleto;

    public Usuario() {
    }

    public Usuario(String usuario, String clave, String nombreCompleto) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
