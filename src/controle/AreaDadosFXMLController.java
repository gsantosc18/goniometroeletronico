/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.dao.PacienteDAO;
import modelo.entity.Paciente;
import modelo.util.ListView;
import modelo.util.Scenario;

/**
 * FXML Controller class
 *
 * @author myhouse
 */
public class AreaDadosFXMLController implements Initializable {
    @FXML TableView<ListView> listaPacientes;
    
    @FXML private JFXTextField inputNome;
    @FXML private JFXTextField inputAltura;
    @FXML private JFXTextField inputNascimento;
    @FXML private JFXTextField inputIdade;
    @FXML private JFXTextArea txtObs;
    @FXML private JFXComboBox<String> comboSexo;
    @FXML
    private Label lbName;
    @FXML
    private JFXTextField inputSearch;
    @FXML
    private JFXButton btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        disableInputs();
        
        comboSexo.getItems().addAll("Masculino","Feminino");
        
        TableColumn<ListView, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        id.setEditable(false);
        id.setResizable(false);
        id.setMinWidth(40);
        id.setMaxWidth(40);
        
        TableColumn<ListView, String> nome = new TableColumn<>("Nome");
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nome.setEditable(false);
        nome.setResizable(false);
        nome.setMinWidth(500);
        nome.setMaxWidth(500);
        
        ObservableList<ListView> lista = FXCollections.observableArrayList();
            new Thread(){
                @Override
                public void run(){
                    Platform.runLater(()->{
                        PacienteDAO pacienteDao = new PacienteDAO();

                        for(Paciente paciente : pacienteDao.all()){
                                String nome = paciente.getNome();
                                int id = paciente.getIdPaciente();
                                lista.add(new ListView(id, nome));
                        }
                        
                        listaPacientes.setItems(lista);
                        
                    }); 
                }
            }.start();
        
        listaPacientes.getColumns().addAll(id,nome);
        
        ObservableList selectedCells = listaPacientes.getSelectionModel().getSelectedCells();

            selectedCells.addListener(new ListChangeListener() {
                @Override
                public void onChanged(Change c) {                    
                    int id = listaPacientes.getSelectionModel().getSelectedItem().getID();
                    
                    if(id>0){
                        
                        PacienteDAO pacientedao = new PacienteDAO();
                        Paciente paciente = pacientedao.findById(id);
                        
                        inputIdade.setText(paciente.getIdade().toString());
                        inputNascimento.setText(paciente.getNascimento().toString());
                        inputNome.setText(paciente.getNome());
                        inputAltura.setText(paciente.getAltura().toString());
                        txtObs.setText(paciente.getObservacao());
                        comboSexo.setValue(paciente.getSexo());
                    }
                }
            });
    }

    @FXML void searchAction(){
        
    }
    
    @FXML void logoutAction() throws IOException{
        Scenario.show(AreaDadosFXMLController.class.getClass().getResource("/visao/LoginFXML.fxml"));
    }
    
    @FXML void createAction() throws IOException{
        Scenario.show(LoginFXMLController.class.getClass().getResource("/visao/CriaPacienteFXML.fxml"));
    }
    
    @FXML void iniciarSessao() throws IOException{
        String comando = "C:\\kinect\\VitruviusTest.exe";
        Process processo = Runtime.getRuntime().exec(comando);
    }
    
    @FXML void actionEditar(){
        enableInputs();
    }
    
    @FXML void actionUpdate(){

        
        
    }
    
    private void enableInputs(){
//        Nome
        inputNome.setEditable(true);
        inputNome.setDisable(false);
//        Altura
        inputAltura.setEditable(true);
        inputAltura.setDisable(false);
//        Nascimento
        inputNascimento.setEditable(true);
        inputNascimento.setDisable(false);
//        Idade
        inputIdade.setEditable(true);
        inputIdade.setDisable(false);
//        Observacao
        txtObs.setEditable(true);
        txtObs.setDisable(false);
//        Combobox
        comboSexo.setEditable(true);
        comboSexo.setDisable(false);
    }
    
    private void disableInputs(){
//        Nome
        inputNome.setEditable(false);
        inputNome.setDisable(true);
//        Altura
        inputAltura.setEditable(false);
        inputAltura.setDisable(true);
//        Nascimento
        inputNascimento.setEditable(false);
        inputNascimento.setDisable(true);
//        Idade
        inputIdade.setEditable(false);
        inputIdade.setDisable(true);
//        Observacao
        txtObs.setEditable(false);
        txtObs.setDisable(true);
//        Combobox
        comboSexo.setEditable(false);
        comboSexo.setDisable(true);
    }
}
