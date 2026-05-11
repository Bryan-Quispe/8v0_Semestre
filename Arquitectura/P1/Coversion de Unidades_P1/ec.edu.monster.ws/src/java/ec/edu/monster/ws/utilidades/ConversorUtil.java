package ec.edu.monster.ws.utilidades;

/**
 * Utilidad con métodos estáticos para conversión de unidades.
 * Contiene la lógica de conversión de longitud, masa y temperatura.
 * @author BRYAN-PC
 */
public class ConversorUtil {

    // =========================================================
    //  LONGITUD
    //  Unidades soportadas: m, km, cm, mm, in
    // =========================================================
    public static double convertirLongitud(double valor, String desde, String hasta) {
        // 1) Convertir a metros
        double metros;
        switch (desde.toLowerCase()) {
            case "km": metros = valor * 1000.0;   break;
            case "cm": metros = valor / 100.0;    break;
            case "mm": metros = valor / 1000.0;   break;
            case "in": metros = valor * 0.0254;   break;
            default:   metros = valor;             break; // "m"
        }
        // 2) Convertir de metros a destino
        switch (hasta.toLowerCase()) {
            case "km": return metros / 1000.0;
            case "cm": return metros * 100.0;
            case "mm": return metros * 1000.0;
            case "in": return metros / 0.0254;
            default:   return metros;              // "m"
        }
    }

    // =========================================================
    //  MASA
    //  Unidades soportadas: kg, g, mg, t, lb
    // =========================================================
    public static double convertirMasa(double valor, String desde, String hasta) {
        // 1) Convertir a kilogramos
        double kg;
        switch (desde.toLowerCase()) {
            case "g":  kg = valor / 1000.0;         break;
            case "mg": kg = valor / 1_000_000.0;    break;
            case "t":  kg = valor * 1000.0;         break;
            case "lb": kg = valor * 0.453592;       break;
            default:   kg = valor;                  break; // "kg"
        }
        // 2) Convertir de kg a destino
        switch (hasta.toLowerCase()) {
            case "g":  return kg * 1000.0;
            case "mg": return kg * 1_000_000.0;
            case "t":  return kg / 1000.0;
            case "lb": return kg / 0.453592;
            default:   return kg;                   // "kg"
        }
    }

    // =========================================================
    //  TEMPERATURA
    //  Unidades soportadas: C, F, K, R (Rankine), Re (Réaumur)
    // =========================================================
    public static double convertirTemperatura(double valor, String desde, String hasta) {
        // 1) Convertir a Celsius
        double celsius;
        switch (desde.toUpperCase()) {
            case "F":  celsius = (valor - 32.0) * 5.0 / 9.0;  break;
            case "K":  celsius = valor - 273.15;               break;
            case "R":  celsius = (valor - 491.67) * 5.0 / 9.0; break;
            case "RE": celsius = valor * 5.0 / 4.0;           break;
            default:   celsius = valor;                        break; // "C"
        }
        // 2) Convertir de Celsius a destino
        switch (hasta.toUpperCase()) {
            case "F":  return celsius * 9.0 / 5.0 + 32.0;
            case "K":  return celsius + 273.15;
            case "R":  return (celsius + 273.15) * 9.0 / 5.0;
            case "RE": return celsius * 4.0 / 5.0;
            default:   return celsius;                         // "C"
        }
    }

    /**
     * Devuelve las unidades disponibles por categoría.
     */
    public static String obtenerUnidades(String categoria) {
        switch (categoria.toLowerCase()) {
            case "longitud":    return "m,km,cm,mm,in";
            case "masa":        return "kg,g,mg,t,lb";
            case "temperatura": return "C,F,K,R,Re";
            default:            return "Categorias: longitud, masa, temperatura";
        }
    }
}
