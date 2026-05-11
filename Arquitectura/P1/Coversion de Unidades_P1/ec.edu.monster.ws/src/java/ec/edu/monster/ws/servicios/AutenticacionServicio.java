package ec.edu.monster.ws.servicios;

import ec.edu.monster.ws.modelos.RespuestaLogin;
import ec.edu.monster.ws.repositorios.UsuarioRepositorio;

/**
 * Servicio de autenticación.
 * Valida credenciales contra el repositorio en memoria.
 * @author BRYAN-PC
 */
public class AutenticacionServicio {

    private final UsuarioRepositorio repositorio;

    public AutenticacionServicio() {
        this.repositorio = new UsuarioRepositorio();
    }

    /**
     * Autentica un usuario con sus credenciales.
     * @param usuario nombre de usuario
     * @param clave contraseña en texto plano
     * @return RespuestaLogin con el resultado
     */
    public RespuestaLogin autenticar(String usuario, String clave) {
        if (usuario == null || clave == null || usuario.isEmpty() || clave.isEmpty()) {
            return new RespuestaLogin(false, "Usuario y contraseña son requeridos");
        }

        boolean valido = repositorio.validarCredenciales(usuario, clave);
        if (valido) {
            return new RespuestaLogin(true, "Autenticación exitosa");
        } else {
            return new RespuestaLogin(false, "Credenciales incorrectas");
        }
    }
}
