package ec.edu.monster.ws.cliente.consola.vistas;

/**
 * Vista de consola - maneja la presentación de la interfaz en terminal.
 * Diseño consistente con los demás clientes.
 * @author BRYAN-PC
 */
public class ConsolaVista {

    private static final String LINEA = "════════════════════════════════════════════════════════";
    private static final String LINEA_SIMPLE = "────────────────────────────────────────────────────────";

    /**
     * Muestra la cabecera del sistema.
     */
    public void mostrarCabecera() {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("   ⚡ CONVERSOR DE UNIDADES SOAP - Grupo 04 ⚡");
        System.out.println("   ec.edu.monster.ws - Cliente Consola");
        System.out.println(LINEA);
        System.out.println();
    }

    /**
     * Muestra la pantalla de login.
     */
    public void mostrarLoginHeader() {
        System.out.println(LINEA_SIMPLE);
        System.out.println("   🔐 INICIAR SESIÓN");
        System.out.println(LINEA_SIMPLE);
    }

    /**
     * Muestra el menú principal.
     */
    public void mostrarMenuPrincipal(String usuario) {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("   ✅ Bienvenido, " + usuario);
        System.out.println(LINEA);
        System.out.println();
        System.out.println("   Seleccione una categoría:");
        System.out.println();
        System.out.println("   [1] 📏 Longitud    (m, km, cm, mm, in)");
        System.out.println("   [2] ⚖️  Masa        (kg, g, mg, t, lb)");
        System.out.println("   [3] 🌡️  Temperatura (C, F, K, R, Re)");
        System.out.println("   [4] 🚪 Cerrar sesión");
        System.out.println();
        System.out.print("   Opción: ");
    }

    /**
     * Muestra las unidades disponibles para una categoría.
     */
    public void mostrarUnidades(String categoria, String unidades) {
        System.out.println();
        System.out.println("   📋 Unidades de " + categoria + ": " + unidades);
        System.out.println(LINEA_SIMPLE);
    }

    /**
     * Muestra el resultado de la conversión.
     */
    public void mostrarResultado(double valor, String desde, double resultado, String hasta) {
        System.out.println();
        System.out.println(LINEA_SIMPLE);
        java.text.DecimalFormat formato = new java.text.DecimalFormat("#.######");
        System.out.printf("   📊 RESULTADO: %s %s = %s %s%n", formato.format(valor), desde, formato.format(resultado), hasta);
        System.out.println(LINEA_SIMPLE);
        System.out.println();
    }

    /**
     * Muestra un mensaje de error.
     */
    public void mostrarError(String mensaje) {
        System.out.println();
        System.out.println("   ❌ ERROR: " + mensaje);
        System.out.println();
    }

    /**
     * Muestra un mensaje de éxito.
     */
    public void mostrarExito(String mensaje) {
        System.out.println();
        System.out.println("   ✅ " + mensaje);
        System.out.println();
    }

    /**
     * Muestra el mensaje de despedida.
     */
    public void mostrarDespedida() {
        System.out.println();
        System.out.println(LINEA);
        System.out.println("   👋 ¡Hasta luego! Sesión cerrada.");
        System.out.println(LINEA);
        System.out.println();
    }
}
