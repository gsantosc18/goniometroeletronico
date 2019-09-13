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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import modelo.dao.PacienteDAO;
import modelo.entity.Paciente;
import modelo.util.Scenario;

/**
 * FXML Controller class
 *
 * @author Myhouse
 */
public class CriaPacienteFXMLController implements Initializable {

    @FXML
    private JFXTextField inputNome;
    @FXML
    private JFXTextField inputAltura;
    @FXML
    private JFXTextField inputNascimento;
    @FXML
    private JFXTextField inputIdade;
    @FXML
    private JFXComboBox<String> comboSexo;
    @FXML
    private JFXTextArea txtObservacao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboSexo.getItems().addAll("Masculino","Feminino");
    }

    @FXML
    private void actionSalvar() throws ParseException, IOException {
        salvar();
    }

    @FXML
    private void actionCancelar() throws IOException {
        Scenario.show(AreaDadosFXMLController.class.getClass().getResource("/visao/AreaDadosFXML.fxml"));
    }
    
    
    private void salvar() throws ParseException, IOException{
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente paciente = new Paciente();
        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date(format.parse(inputNascimento.getText()).getTime());
        
        paciente.setNome(inputNome.getText());
        paciente.setNascimento(data);
        paciente.setAltura(Double.parseDouble(inputAltura.getText()));
        paciente.setObservacao(txtObservacao.getText());
        paciente.setIdade(Integer.parseInt(inputIdade.getText()));
        paciente.setSexo(comboSexo.getValue());
        
        pacienteDAO.add(paciente);
        
        actionCancelar();
    }
}
