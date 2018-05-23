/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import model.util.Notify;
import modelo.dao.UsuarioDAO;
import modelo.entity.Usuario;
import modelo.util.EncryptMD5;
import modelo.util.Scenario;

/**
 * FXML Controller class
 *
 * @author myhouse
 */
public class LoginFXMLController implements Initializable {

    @FXML JFXTextField inputEmail;
    @FXML JFXPasswordField inputSenha;
    
    @FXML JFXTextField inputNomeCd;
    @FXML JFXTextField inputEmailCd;
    @FXML JFXPasswordField inputSenhaCd;
    @FXML JFXPasswordField inputConfirmSenhaCd;
    
    @FXML Accordion acordeon;
    @FXML TitledPane tpLogin;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acordeon.setExpandedPane(tpLogin);
        
        Platform.runLater(()->inputEmail.requestFocus());
    }

    @FXML void loginAction() throws NoSuchAlgorithmException, IOException{
        if(!isInputValid(inputEmail)||!isInputValid(inputSenha)){
            Notify.erro("Preencha corretamente os campos Email e Senha!");
        }else{
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.login(inputEmail.getText(), inputSenha.getText());
            
            if(usuario!=null){
                Scenario.show(LoginFXMLController.class.getClass().getResource("/visao/AreaDadosFXML.fxml"));
            }else{
                Notify.erro("Email ou Senha inválidos!");
            }
        }
    }
    
    @FXML void cadastrarAction() throws NoSuchAlgorithmException{
        if(!isInputValid(inputEmailCd)||!isInputValid(inputSenhaCd)||!isInputValid(inputConfirmSenhaCd)){
            Notify.erro("Preencha corretamente os campos Email, Senha e Confirmação de Senha!");
        }else if(!inputSenhaCd.getText().equals(inputConfirmSenhaCd.getText())){
            Notify.erro("As senhas não são iguais!");
        }else{
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            if(usuarioDAO.findByEmail(inputEmailCd.getText())!=null){
                Notify.erro("O email pretendido já está cadastrado!");
            }else{
                Usuario usuario = new Usuario();
                usuario.setEmail(inputEmailCd.getText());
                usuario.setSenha(EncryptMD5.encrypt(inputSenhaCd.getText()));
                usuario.setNome(inputNomeCd.getText());
                usuarioDAO.add(usuario);
                
                Notify.info("O cadastro foi realizado com sucesso!");
                tpLogin.setExpanded(true);
            }
        }
    }
    
    private boolean isInputValid(TextField input){
        return !input.getText().trim().isEmpty();
    }
}
