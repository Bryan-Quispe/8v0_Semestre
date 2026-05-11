package ec.edu.monster.ws.cliente.escritorio.controladores;

import ec.edu.monster.ws.cliente.escritorio.servicios.SoapClienteServicio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla de conversión en JavaFX.
 * @author BRYAN-PC
 */
public class ConversionControlador {

    @FXML private Label lblUsuario;
    @FXML private ComboBox<String> cmbCategoria;
    @FXML private TextField txtValor;
    @FXML private ComboBox<String> cmbDesde;
    @FXML private ComboBox<String> cmbHasta;
    @FXML private Label lblResultado;
    @FXML private Label lblError;
    @FXML private Button btnConvertir;
    @FXML private Button btnSalir;

    private SoapClienteServicio servicio;
    private String usuario;

    @FXML
    public void initialize() {
        cmbCategoria.getItems().addAll("longitud", "masa", "temperatura");
        
        cmbCategoria.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            actualizarUnidades(newVal);
        });

        cmbCategoria.setValue("longitud");
        lblResultado.setVisible(false);
        lblError.setVisible(false);
    }

    private void actualizarUnidades(String categoria) {
        if (categoria == null) return;
        cmbDesde.getItems().clear();
        cmbHasta.getItems().clear();
        
        String[] unidades = {};
        switch (categoria) {
            case "longitud": unidades = new String[]{"m", "km", "cm", "mm", "in"}; break;
            case "masa": unidades = new String[]{"kg", "g", "mg", "t", "lb"}; break;
            case "temperatura": unidades = new String[]{"C", "F", "K", "R", "Re"}; break;
        }
        
        cmbDesde.getItems().addAll(unidades);
        cmbHasta.getItems().addAll(unidades);
        
        if (unidades.length > 0) {
            cmbDesde.getSelectionModel().selectFirst();
            cmbHasta.getSelectionModel().selectFirst();
        }
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
        lblUsuario.setText("👤 " + usuario);
    }

    public void setServicio(SoapClienteServicio servicio) {
        this.servicio = servicio;
    }

    @FXML
    private void procesarConversion() {
        lblError.setVisible(false);
        lblResultado.setVisible(false);

        try {
            String categoria = cmbCategoria.getValue();
            double valor = Double.parseDouble(txtValor.getText().trim());
            String desde = cmbDesde.getValue();
            String hasta = cmbHasta.getValue();

            if (desde == null || hasta == null || desde.isEmpty() || hasta.isEmpty()) {
                mostrarError("Seleccione las unidades de origen y destino");
                return;
            }

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
                    mostrarError("Categoría no válida");
                    return;
            }

            java.text.DecimalFormat formato = new java.text.DecimalFormat("#.######");
            String valorFmt = formato.format(valor);
            String resultadoFmt = formato.format(resultado);

            lblResultado.setText(String.format("📊 %s %s = %s %s", valorFmt, desde, resultadoFmt, hasta));
            lblResultado.setVisible(true);

        } catch (NumberFormatException e) {
            mostrarError("Ingrese un valor numérico válido");
        } catch (Exception e) {
            mostrarError("Error: " + e.getMessage());
        }
    }

    @FXML
    private void cerrarSesion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/vistas/login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnSalir.getScene().getWindow();
            Scene scene = new Scene(root, 450, 400);
            scene.getStylesheets().add(
                    getClass().getResource("/css/estilos.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Conversor de Unidades - Login");
        } catch (Exception e) {
            mostrarError("Error al cerrar sesión: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        lblError.setText("❌ " + mensaje);
        lblError.setVisible(true);
    }
}
