package ec.edu.monster.ws.configuracion;

/**
 * Constantes y configuración del servicio web SOAP.
 * @author BRYAN-PC
 */
public class ConfiguracionServicio {

    /** Nombre del servicio web */
    public static final String NOMBRE_SERVICIO = "WS";

    /** Espacio de nombres del servicio */
    public static final String NAMESPACE = "http://ws.monster.edu.ec/";

    /** Versión del servicio */
    public static final String VERSION = "1.0.0";

    /** Mensaje de bienvenida */
    public static final String MENSAJE_BIENVENIDA = "Conversor de Unidades SOAP - Grupo 04";

    /** Categorías disponibles */
    public static final String[] CATEGORIAS = {"longitud", "masa", "temperatura"};

    private ConfiguracionServicio() {
        // Clase utilitaria, no instanciar
    }
}
