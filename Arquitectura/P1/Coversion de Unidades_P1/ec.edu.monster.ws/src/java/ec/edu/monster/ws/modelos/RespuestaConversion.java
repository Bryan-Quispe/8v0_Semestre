package ec.edu.monster.ws.modelos;

/**
 * Modelo de respuesta para una conversión de unidades.
 * @author BRYAN-PC
 */
public class RespuestaConversion {

    private boolean exitoso;
    private double resultado;
    private String mensaje;

    public RespuestaConversion() {
    }

    public RespuestaConversion(boolean exitoso, double resultado, String mensaje) {
        this.exitoso = exitoso;
        this.resultado = resultado;
        this.mensaje = mensaje;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
