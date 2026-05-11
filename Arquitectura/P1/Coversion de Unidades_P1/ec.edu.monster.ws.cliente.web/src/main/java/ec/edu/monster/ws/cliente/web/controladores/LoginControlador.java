package ec.edu.monster.ws.cliente.web.controladores;

import ec.edu.monster.ws.cliente.web.modelos.LoginFormulario;
import ec.edu.monster.ws.cliente.web.servicios.SoapClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

/**
 * Controlador de autenticación para el cliente web.
 * @author BRYAN-PC
 */
@Controller
public class LoginControlador {

    @Autowired
    private SoapClienteServicio soapServicio;

    @GetMapping("/")
    public String mostrarLogin(Model modelo) {
        modelo.addAttribute("loginFormulario", new LoginFormulario());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute LoginFormulario loginFormulario,
                                Model modelo, HttpSession sesion) {
        try {
            String resultado = soapServicio.autenticar(
                    loginFormulario.getUsuario(),
                    loginFormulario.getClave());

            if ("AUTENTICADO".equals(resultado)) {
                sesion.setAttribute("usuario", loginFormulario.getUsuario());
                sesion.setAttribute("autenticado", true);
                return "redirect:/conversion";
            } else {
                modelo.addAttribute("error", "Credenciales incorrectas");
                modelo.addAttribute("loginFormulario", loginFormulario);
                return "login";
            }
        } catch (Exception e) {
            modelo.addAttribute("error", "Error de conexión: " + e.getMessage());
            modelo.addAttribute("loginFormulario", loginFormulario);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/";
    }
}
