package ec.edu.monster.ws.cliente.escritorio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación JavaFX.
 * Cliente de escritorio del conversor de unidades SOAP.
 * @author BRYAN-PC
 */
public class AplicacionEscritorio extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/vistas/login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 450, 400);
        scene.getStylesheets().add(
                getClass().getResource("/css/estilos.css").toExternalForm());

        primaryStage.setTitle("Conversor de Unidades SOAP - Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
