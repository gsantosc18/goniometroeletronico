package modelo.entity;
// Generated 23/05/2018 05:49:30 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Paciente generated by hbm2java
 */
public class Paciente  implements java.io.Serializable {


     private Integer idPaciente;
     private String nome;
     private Integer idade;
     private Double altura;
     private Date nascimento;
     private String sexo;
     private String observacao;

    public Paciente() {
    }

    public Paciente(String nome, Integer idade, Double altura, Date nascimento, String sexo, String observacao) {
       this.nome = nome;
       this.idade = idade;
       this.altura = altura;
       this.nascimento = nascimento;
       this.sexo = sexo;
       this.observacao = observacao;
    }
   
    public Integer getIdPaciente() {
        return this.idPaciente;
    }
    
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getIdade() {
        return this.idade;
    }
    
    public void setIdade(Integer idade) {
        this.idade = idade;
    }
    public Double getAltura() {
        return this.altura;
    }
    
    public void setAltura(Double altura) {
        this.altura = altura;
    }
    public Date getNascimento() {
        return this.nascimento;
    }
    
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }




}

