package ec.edu.monster.ws.modelos;

/**
 * Modelo que encapsula una solicitud de conversión de unidades.
 * @author BRYAN-PC
 */
public class SolicitudConversion {

    private String categoria;
    private double valor;
    private String desde;
    private String hasta;

    public SolicitudConversion() {
    }

    public SolicitudConversion(String categoria, double valor, String desde, String hasta) {
        this.categoria = categoria;
        this.valor = valor;
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }
}
