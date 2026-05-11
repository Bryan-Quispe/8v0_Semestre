package ec.edu.monster.ws.cliente.web.servicios;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Servicio que consume el web service SOAP de conversión de unidades.
 * Construye las peticiones XML manualmente (sin WSDL generado).
 * @author BRYAN-PC
 */
@Service
public class SoapClienteServicio {

    private static final String NAMESPACE = "http://ws.monster.edu.ec/";

    @Value("${soap.servicio.url}")
    private String urlServicio;

    /**
     * Autentica al usuario contra el servicio SOAP.
     */
    public String autenticar(String usuario, String clave) throws Exception {
        String soapXml = construirEnvelope(
                "<ws:autenticar>"
                + "<usuario>" + usuario + "</usuario>"
                + "<clave>" + clave + "</clave>"
                + "</ws:autenticar>");

        String respuesta = enviarPeticion(soapXml);
        return extraerValor(respuesta, "return");
    }

    /**
     * Convierte longitud.
     */
    public double convertirLongitud(double valor, String desde, String hasta) throws Exception {
        return convertir("convertirLongitud", valor, desde, hasta);
    }

    /**
     * Convierte masa.
     */
    public double convertirMasa(double valor, String desde, String hasta) throws Exception {
        return convertir("convertirMasa", valor, desde, hasta);
    }

    /**
     * Convierte temperatura.
     */
    public double convertirTemperatura(double valor, String desde, String hasta) throws Exception {
        return convertir("convertirTemperatura", valor, desde, hasta);
    }

    /**
     * Obtiene unidades disponibles.
     */
    public String obtenerUnidades(String categoria) throws Exception {
        String soapXml = construirEnvelope(
                "<ws:obtenerUnidades>"
                + "<categoria>" + categoria + "</categoria>"
                + "</ws:obtenerUnidades>");

        String respuesta = enviarPeticion(soapXml);
        return extraerValor(respuesta, "return");
    }

    // ========== Métodos privados ==========

    private double convertir(String operacion, double valor, String desde, String hasta) throws Exception {
        String soapXml = construirEnvelope(
                "<ws:" + operacion + ">"
                + "<valor>" + valor + "</valor>"
                + "<desde>" + desde + "</desde>"
                + "<hasta>" + hasta + "</hasta>"
                + "</ws:" + operacion + ">");

        String respuesta = enviarPeticion(soapXml);
        String valorStr = extraerValor(respuesta, "return");
        return Double.parseDouble(valorStr);
    }

    private String construirEnvelope(String cuerpo) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                + "xmlns:ws=\"" + NAMESPACE + "\">"
                + "<soapenv:Body>"
                + cuerpo
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
    }

    private String enviarPeticion(String soapXml) throws Exception {
        URL url = new URL(urlServicio);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("POST");
        conexion.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conexion.setDoOutput(true);
        conexion.setConnectTimeout(5000);
        conexion.setReadTimeout(5000);

        try (OutputStream os = conexion.getOutputStream()) {
            os.write(soapXml.getBytes("UTF-8"));
            os.flush();
        }

        int codigo = conexion.getResponseCode();
        InputStream is = (codigo >= 200 && codigo < 300)
                ? conexion.getInputStream() : conexion.getErrorStream();

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

    private String extraerValor(String xml, String etiqueta) {
        String[] patrones = {"<" + etiqueta + ">", "<ns2:" + etiqueta + ">", "<return>"};
        String[] cierres = {"</" + etiqueta + ">", "</ns2:" + etiqueta + ">", "</return>"};

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
