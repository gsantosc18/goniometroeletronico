
import controle.Principal;
import java.io.IOException;
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
public class TestaAreaDados{

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Scenario.setPrimaStage(primaryStage);
//        Scenario.show(Principal.class.getClass().getResource("/visao/CriaPacienteFXML.fxml"));
//    }

    public static void main(String[] args) throws IOException {
        String comando = "C:\\kinect\\VitruviusTest.exe";
        Process processo = Runtime.getRuntime().exec(comando);
//        launch(args);
    }
    
}
