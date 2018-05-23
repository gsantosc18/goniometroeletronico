package modelo.util;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Scenario {
    
    private static Scene scene;
    private static Stage primaStage;

    /**
     * @return the scene
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * @param aScene the scene to set
     */
    public static void setScene(Scene aScene) {
        scene = aScene;
    }

    /**
     * @return the primaStage
     */
    public static Stage getPrimaStage() {
        return primaStage;
    }

    /**
     * @param aPrimaStage the primaStage to set
     */
    public static void setPrimaStage(Stage aPrimaStage) {
        primaStage = aPrimaStage;
    }

    private Scenario(){}
    /**
     * @throws IOException 
     */
    public static void show() throws IOException{
        Scene scena = getPrimaStage().getScene();
        getPrimaStage().centerOnScreen();
        getPrimaStage().sizeToScene();
        getPrimaStage().minWidthProperty().bind(scena.widthProperty());
        getPrimaStage().minHeightProperty().bind(scena.heightProperty());
        getPrimaStage().show();
        getPrimaStage().requestFocus();
//        primaStage.getIcons().add(new Image(Scenario.class.getClass().getResourceAsStream("/image/iconKey.png")));
    }
    
    /**
     * @param file
     * @throws IOException 
     */
    public static void show(URL file) throws IOException{
            Parent root = FXMLLoader.load(file);
            setScene(new Scene(root));        
            getPrimaStage().setScene(getScene());
            show();
    }
    
    /**
     * @param file
     * @param controller 
     */
    public static void show(URL file, Object controller) throws IOException{      
            FXMLLoader loader = new FXMLLoader(file);
            loader.setController(controller);
            Parent root = loader.load();
            scene = new Scene(root);        
            primaStage.setScene(scene);
            show();
    }
    
}
