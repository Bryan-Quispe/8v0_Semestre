package ec.edu.monster.ws.cliente.web.controladores;

import ec.edu.monster.ws.cliente.web.modelos.ConversionFormulario;
import ec.edu.monster.ws.cliente.web.servicios.SoapClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;

/**
 * Controlador de conversión de unidades para el cliente web.
 * @author BRYAN-PC
 */
@Controller
public class ConversionControlador {

    @Autowired
    private SoapClienteServicio soapServicio;

    @GetMapping("/conversion")
    public String mostrarConversion(Model modelo, HttpSession sesion) {
        // Verificar sesión
        Boolean autenticado = (Boolean) sesion.getAttribute("autenticado");
        if (autenticado == null || !autenticado) {
            return "redirect:/";
        }

        modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
        modelo.addAttribute("conversionFormulario", new ConversionFormulario());
        return "conversion";
    }

    @PostMapping("/convertir")
    public String procesarConversion(@ModelAttribute ConversionFormulario formulario,
                                     Model modelo, HttpSession sesion) {
        // Verificar sesión
        Boolean autenticado = (Boolean) sesion.getAttribute("autenticado");
        if (autenticado == null || !autenticado) {
            return "redirect:/";
        }

        try {
            double resultado;
            switch (formulario.getCategoria().toLowerCase()) {
                case "longitud":
                    resultado = soapServicio.convertirLongitud(
                            formulario.getValor(), formulario.getDesde(), formulario.getHasta());
                    break;
                case "masa":
                    resultado = soapServicio.convertirMasa(
                            formulario.getValor(), formulario.getDesde(), formulario.getHasta());
                    break;
                case "temperatura":
                    resultado = soapServicio.convertirTemperatura(
                            formulario.getValor(), formulario.getDesde(), formulario.getHasta());
                    break;
                default:
                    modelo.addAttribute("error", "Categoría no válida");
                    modelo.addAttribute("conversionFormulario", formulario);
                    modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
                    return "conversion";
            }

            java.text.DecimalFormat formato = new java.text.DecimalFormat("#.######");
            modelo.addAttribute("resultado", formato.format(resultado));
            modelo.addAttribute("valorOriginal", formato.format(formulario.getValor()));
            modelo.addAttribute("unidadDesde", formulario.getDesde());
            modelo.addAttribute("unidadHasta", formulario.getHasta());
            modelo.addAttribute("categoria", formulario.getCategoria());
            modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
            modelo.addAttribute("conversionFormulario", formulario);
            return "conversion";

        } catch (Exception e) {
            modelo.addAttribute("error", "Error en la conversión: " + e.getMessage());
            modelo.addAttribute("conversionFormulario", formulario);
            modelo.addAttribute("usuario", sesion.getAttribute("usuario"));
            return "conversion";
        }
    }
}
