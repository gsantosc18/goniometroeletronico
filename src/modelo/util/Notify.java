package model.util;

import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Notify {
    static private Alert alert;
    
    public static void erro(String message){
        Platform.runLater(()->alert(AlertType.ERROR, message, "Erro", true));        
    }
    
    public static void info(String message){
        Platform.runLater(()->alert(AlertType.INFORMATION, message, "Informação", true));
    }
    
    public static void warning(String message){
        Platform.runLater(()->alert(AlertType.WARNING, message, "Atenção", true));
    }
    
    public static Optional<ButtonType> confirm(String message, String header){
        return alert(AlertType.CONFIRMATION, message, header, false);
    }
    
    private static Optional<ButtonType> alert(AlertType type, String message, String title, boolean onlyShow){
        alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        if(onlyShow){
            alert.show();
            return null;
        }
        return alert.showAndWait();
    }
}
