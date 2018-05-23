package controle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.util.Scenario;

public class Principal extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scenario.setPrimaStage(primaryStage);
        Scenario.show(Principal.class.getClass().getResource("/visao/LoginFXML.fxml"));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
