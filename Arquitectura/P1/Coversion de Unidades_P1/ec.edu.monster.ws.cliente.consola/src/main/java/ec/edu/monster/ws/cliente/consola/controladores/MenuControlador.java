package ec.edu.monster.ws.cliente.consola.controladores;

import ec.edu.monster.ws.cliente.consola.modelos.SesionUsuario;
import ec.edu.monster.ws.cliente.consola.servicios.SoapClienteServicio;
import ec.edu.monster.ws.cliente.consola.vistas.ConsolaVista;
import java.util.Scanner;

/**
 * Controlador del menú principal de la consola.
 * Orquesta la interacción entre la vista, el modelo y el servicio SOAP.
 * @author BRYAN-PC
 */
public class MenuControlador {

    private final SoapClienteServicio servicio;
    private final ConsolaVista vista;
    private final SesionUsuario sesion;
    private final Scanner scanner;

    public MenuControlador(SoapClienteServicio servicio) {
        this.servicio = servicio;
        this.vista = new ConsolaVista();
        this.sesion = new SesionUsuario();
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    /**
     * Inicia la aplicación de consola.
     */
    public void iniciar() {
        vista.mostrarCabecera();

        // Ciclo de login
        while (!sesion.isAutenticado()) {
            procesarLogin();
        }

        // Ciclo principal
        boolean ejecutando = true;
        while (ejecutando) {
            vista.mostrarMenuPrincipal(sesion.getUsuario());
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    procesarConversion("longitud", "convertirLongitud");
                    break;
                case "2":
                    procesarConversion("masa", "convertirMasa");
                    break;
                case "3":
                    procesarConversion("temperatura", "convertirTemperatura");
                    break;
                case "4":
                    ejecutando = false;
                    vista.mostrarDespedida();
                    break;
                default:
                    vista.mostrarError("Opción no válida. Intente de nuevo.");
            }
        }
    }

    /**
     * Procesa el inicio de sesión.
     */
    private void procesarLogin() {
        vista.mostrarLoginHeader();
        System.out.print("   Usuario: ");
        String usuario = scanner.nextLine().trim();
        System.out.print("   Contraseña: ");
        String clave = scanner.nextLine().trim();

        try {
            String resultado = servicio.autenticar(usuario, clave);
            if ("AUTENTICADO".equals(resultado)) {
                sesion.setUsuario(usuario);
                sesion.setAutenticado(true);
                vista.mostrarExito("Autenticación exitosa");
            } else {
                vista.mostrarError("Credenciales incorrectas. Intente de nuevo.");
            }
        } catch (Exception e) {
            vista.mostrarError("No se pudo conectar al servidor: " + e.getMessage());
        }
    }

    /**
     * Procesa una conversión de unidades.
     */
    private void procesarConversion(String categoria, String operacion) {
        try {
            // Mostrar unidades disponibles
            String unidades = servicio.obtenerUnidades(categoria);
            vista.mostrarUnidades(categoria, unidades);

            System.out.print("   Valor a convertir: ");
            double valor = Double.parseDouble(scanner.nextLine().trim());

            System.out.print("   Unidad origen (" + unidades + "): ");
            String desde = scanner.nextLine().trim();

            System.out.print("   Unidad destino (" + unidades + "): ");
            String hasta = scanner.nextLine().trim();

            double resultado;
            switch (categoria) {
                case "longitud":
                    resultado = servicio.convertirLongitud(valor, desde, hasta);
                    break;
                case "masa":
                    resultado = servicio.convertirMasa(valor, desde, hasta);
                    break;
                case "temperatura":
                    resultado = servicio.convertirTemperatura(valor, desde, hasta);
                    break;
                default:
                    vista.mostrarError("Categoría no válida");
                    return;
            }

            vista.mostrarResultado(valor, desde, resultado, hasta);

        } catch (NumberFormatException e) {
            vista.mostrarError("El valor ingresado no es un número válido.");
        } catch (Exception e) {
            vista.mostrarError("Error en la conversión: " + e.getMessage());
        }
    }
}
