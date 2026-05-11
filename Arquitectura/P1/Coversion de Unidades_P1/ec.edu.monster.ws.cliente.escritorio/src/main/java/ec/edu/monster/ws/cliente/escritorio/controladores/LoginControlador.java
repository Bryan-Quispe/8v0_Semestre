package ec.edu.monster.ws.cliente.escritorio.controladores;

import ec.edu.monster.ws.cliente.escritorio.servicios.SoapClienteServicio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Controlador de la pantalla de login en JavaFX.
 * @author BRYAN-PC
 */
public class LoginControlador {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtClave;
    @FXML private TextField txtClaveVisible;
    @FXML private CheckBox chkMostrarClave;
    @FXML private Label lblError;
    @FXML private Button btnLogin;

    private SoapClienteServicio servicio;
    
    // URL del servicio SOAP. Cambiar aquí si cambia la IP del servidor
    private static final String URL_SERVICIO = "http://localhost:8080/ec.edu.monster.ws/WS";

    @FXML
    public void initialize() {
        servicio = new SoapClienteServicio(URL_SERVICIO);
        lblError.setVisible(false);

        // Sincronizar campos de contraseña
        txtClaveVisible.textProperty().bindBidirectional(txtClave.textProperty());

        // Lógica del ojito
        chkMostrarClave.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                txtClaveVisible.setVisible(true);
                txtClave.setVisible(false);
            } else {
                txtClaveVisible.setVisible(false);
                txtClave.setVisible(true);
            }
        });
    }

    @FXML
    private void procesarLogin() {
        String usuario = txtUsuario.getText().trim();
        String clave = txtClave.getText().trim();

        if (usuario.isEmpty() || clave.isEmpty()) {
            mostrarError("Ingrese usuario y contraseña");
            return;
        }

        try {
            String resultado = servicio.autenticar(usuario, clave);
            if ("AUTENTICADO".equals(resultado)) {
                abrirConversion(usuario);
            } else {
                mostrarError("Credenciales incorrectas");
            }
        } catch (Exception e) {
            mostrarError("Error de conexión: " + e.getMessage());
        }
    }

    private void abrirConversion(String usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/vistas/conversion.fxml"));
            Parent root = loader.load();

            ConversionControlador controlador = loader.getController();
            controlador.setUsuario(usuario);
            controlador.setServicio(servicio);

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(root, 700, 550);
            scene.getStylesheets().add(
                    getClass().getResource("/css/estilos.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Conversor de Unidades - " + usuario);
        } catch (Exception e) {
            mostrarError("Error al cargar pantalla: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        lblError.setText("❌ " + mensaje);
        lblError.setVisible(true);
    }
}
