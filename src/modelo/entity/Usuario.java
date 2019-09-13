package modelo.entity;
// Generated 25/05/2018 02:01:51 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer idUsuario;
     private String nome;
     private String email;
     private String senha;
     private Date cadastro;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, Date cadastro) {
       this.nome = nome;
       this.email = email;
       this.senha = senha;
       this.cadastro = cadastro;
    }
   
    public Integer getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Date getCadastro() {
        return this.cadastro;
    }
    
    public void setCadastro(Date cadastro) {
        this.cadastro = cadastro;
    }




}


