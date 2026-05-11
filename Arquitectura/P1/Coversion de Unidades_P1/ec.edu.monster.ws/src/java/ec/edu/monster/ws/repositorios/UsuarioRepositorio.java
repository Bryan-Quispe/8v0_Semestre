package ec.edu.monster.ws.repositorios;

import ec.edu.monster.ws.modelos.Usuario;

/**
 * Repositorio de usuarios en memoria (sin base de datos).
 * Almacena las credenciales en texto plano.
 * @author BRYAN-PC
 */
public class UsuarioRepositorio {

    // Usuario único almacenado en texto plano
    private static final Usuario USUARIO_REGISTRADO =
            new Usuario("monster", "monster9", "Usuario Monster");

    /**
     * Busca un usuario por nombre de usuario.
     * @param usuario nombre de usuario a buscar
     * @return el Usuario si existe, null si no
     */
    public Usuario buscarPorUsuario(String usuario) {
        if (USUARIO_REGISTRADO.getUsuario().equals(usuario)) {
            return USUARIO_REGISTRADO;
        }
        return null;
    }

    /**
     * Valida las credenciales de un usuario.
     * @param usuario nombre de usuario
     * @param clave contraseña en texto plano
     * @return true si las credenciales son correctas
     */
    public boolean validarCredenciales(String usuario, String clave) {
        Usuario u = buscarPorUsuario(usuario);
        if (u != null) {
            return u.getClave().equals(clave);
        }
        return false;
    }
}
