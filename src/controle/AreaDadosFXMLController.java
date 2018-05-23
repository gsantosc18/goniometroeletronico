/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.util.ListView;
import modelo.util.Scenario;

/**
 * FXML Controller class
 *
 * @author myhouse
 */
public class AreaDadosFXMLController implements Initializable {
    @FXML TableView<ListView> listaPacientes;
    
    @FXML JFXTextField inputNome;
    @FXML JFXTextField inputAltura;
    @FXML JFXTextField inputNascimento;
    @FXML JFXTextField inputIdade;
    @FXML JFXTextArea txtObs;
    @FXML JFXComboBox comboSexo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn<ListView, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        id.setEditable(false);
        
        TableColumn<ListView, String> nome = new TableColumn<>("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        
        ObservableList<ListView> lista = FXCollections.observableArrayList(
                new ListView(1, "Gedalias"),
                new ListView(2, "Rafael")
        );
        
        listaPacientes.getColumns().addAll(id,nome);
        listaPacientes.setItems(lista);
    }

    @FXML void searchAction(){
        
    }
    
    @FXML void logoutAction() throws IOException{
        Scenario.show(AreaDadosFXMLController.class.getClass().getResource("/visao/LoginFXML.fxml"));
    }
    
    @FXML void createAction(){
        
    }
    
    private void enableInputs(){
        
    }
}
