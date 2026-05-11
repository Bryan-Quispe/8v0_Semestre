package ec.edu.monster.ws.cliente.consola.servicios;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Servicio cliente SOAP que construye y envía peticiones XML
 * al servidor de conversión de unidades.
 * No usa base de datos, la comunicación es pura SOAP/HTTP.
 * @author BRYAN-PC
 */
public class SoapClienteServicio {

    private static final String NAMESPACE = "http://ws.monster.edu.ec/";
    private String urlServicio;

    public SoapClienteServicio(String urlServicio) {
        this.urlServicio = urlServicio;
    }

    /**
     * Autentica al usuario contra el servicio SOAP.
     * @param usuario nombre de usuario
     * @param clave contraseña en texto plano
     * @return "AUTENTICADO" si es correcto, "ERROR: mensaje" si no
     */
    public String autenticar(String usuario, String clave) throws Exception {
        String soapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                + "xmlns:ws=\"" + NAMESPACE + "\">"
                + "<soapenv:Body>"
                + "<ws:autenticar>"
                + "<usuario>" + usuario + "</usuario>"
                + "<clave>" + clave + "</clave>"
                + "</ws:autenticar>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        String respuesta = enviarPeticion(soapXml);
        return extraerValor(respuesta, "return");
    }

    /**
     * Convierte longitud a través del servicio SOAP.
     */
    public double convertirLongitud(double valor, String desde, String hasta) throws Exception {
        return convertir("convertirLongitud", valor, desde, hasta);
    }

    /**
     * Convierte masa a través del servicio SOAP.
     */
    public double convertirMasa(double valor, String desde, String hasta) throws Exception {
        return convertir("convertirMasa", valor, desde, hasta);
    }

    /**
     * Convierte temperatura a través del servicio SOAP.
     */
    public double convertirTemperatura(double valor, String desde, String hasta) throws Exception {
        return convertir("convertirTemperatura", valor, desde, hasta);
    }

    /**
     * Obtiene las unidades disponibles para una categoría.
     */
    public String obtenerUnidades(String categoria) throws Exception {
        String soapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                + "xmlns:ws=\"" + NAMESPACE + "\">"
                + "<soapenv:Body>"
                + "<ws:obtenerUnidades>"
                + "<categoria>" + categoria + "</categoria>"
                + "</ws:obtenerUnidades>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        String respuesta = enviarPeticion(soapXml);
        return extraerValor(respuesta, "return");
    }

    // ========== Métodos privados ==========

    private double convertir(String operacion, double valor, String desde, String hasta) throws Exception {
        String soapXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                + "xmlns:ws=\"" + NAMESPACE + "\">"
                + "<soapenv:Body>"
                + "<ws:" + operacion + ">"
                + "<valor>" + valor + "</valor>"
                + "<desde>" + desde + "</desde>"
                + "<hasta>" + hasta + "</hasta>"
                + "</ws:" + operacion + ">"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";

        String respuesta = enviarPeticion(soapXml);
        String valorStr = extraerValor(respuesta, "return");
        return Double.parseDouble(valorStr);
    }

    private String enviarPeticion(String soapXml) throws Exception {
        URL url = new URL(urlServicio);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conexion.setDoOutput(true);
        conexion.setConnectTimeout(5000);
        conexion.setReadTimeout(5000);

        // Enviar petición
        try (OutputStream os = conexion.getOutputStream()) {
            os.write(soapXml.getBytes("UTF-8"));
            os.flush();
        }

        // Leer respuesta
        int codigo = conexion.getResponseCode();
        InputStream is = (codigo >= 200 && codigo < 300)
                ? conexion.getInputStream()
                : conexion.getErrorStream();

        StringBuilder respuesta = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                respuesta.append(linea);
            }
        }

        conexion.disconnect();
        return respuesta.toString();
    }

    /**
     * Extrae el valor de una etiqueta XML de la respuesta SOAP.
     */
    private String extraerValor(String xml, String etiqueta) {
        // Buscar con namespace o sin namespace
        String[] patrones = {
            "<" + etiqueta + ">",
            "<ns2:" + etiqueta + ">",
            "<return>"
        };
        String[] cierres = {
            "</" + etiqueta + ">",
            "</ns2:" + etiqueta + ">",
            "</return>"
        };

        for (int i = 0; i < patrones.length; i++) {
            int inicio = xml.indexOf(patrones[i]);
            if (inicio != -1) {
                inicio += patrones[i].length();
                int fin = xml.indexOf(cierres[i], inicio);
                if (fin != -1) {
                    return xml.substring(inicio, fin);
                }
            }
        }
        return "";
    }
}
