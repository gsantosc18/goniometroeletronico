
import java.security.NoSuchAlgorithmException;
import modelo.dao.UsuarioDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author myhouse
 */
public class TestaLogin {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        UsuarioDAO usuariodao = new UsuarioDAO();
        
        System.out.println(usuariodao.login("gsantos.c18@gmail.com", "24865310"));
    }
}
