package ec.edu.monster.ws.cliente.web.modelos;

/**
 * Modelo de formulario de conversión para Thymeleaf.
 * @author BRYAN-PC
 */
public class ConversionFormulario {

    private String categoria;
    private double valor;
    private String desde;
    private String hasta;

    public ConversionFormulario() {
        this.categoria = "longitud";
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
