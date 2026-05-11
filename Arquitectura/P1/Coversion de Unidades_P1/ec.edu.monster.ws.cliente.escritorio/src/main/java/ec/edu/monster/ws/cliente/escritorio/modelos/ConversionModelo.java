package ec.edu.monster.ws.cliente.escritorio.modelos;

/**
 * Modelo de datos para conversión en JavaFX.
 * @author BRYAN-PC
 */
public class ConversionModelo {

    private String categoria;
    private double valor;
    private String desde;
    private String hasta;
    private double resultado;

    public ConversionModelo() {
        this.categoria = "longitud";
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getDesde() { return desde; }
    public void setDesde(String desde) { this.desde = desde; }

    public String getHasta() { return hasta; }
    public void setHasta(String hasta) { this.hasta = hasta; }

    public double getResultado() { return resultado; }
    public void setResultado(double resultado) { this.resultado = resultado; }
}
