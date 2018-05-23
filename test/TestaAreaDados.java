
import controle.Principal;
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.util.Scenario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author myhouse
 */
public class TestaAreaDados extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scenario.setPrimaStage(primaryStage);
        Scenario.show(Principal.class.getClass().getResource("/visao/AreaDadosFXML.fxml"));
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
