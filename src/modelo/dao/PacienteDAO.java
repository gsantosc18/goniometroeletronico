/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.util.List;
import modelo.entity.Paciente;
import modelo.entity.Usuario;
import modelo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author myhouse
 */
public class PacienteDAO implements Entity {

    @Override
    public void add(Object o) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        session.save((Paciente) o);
        transacao.commit();
    }

    @Override
    public void update(Object o) {
        
    }

    @Override
    public void delete(Object o) {
        
    }
    
    public List<Paciente> all(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        List<Paciente> pacientes = session.createQuery("from Paciente").list();
        transacao.commit();
        
        return pacientes;
    }
    
    public Paciente findById(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        Paciente paciente = (Paciente) session.get(Paciente.class,id);;
        transacao.commit();
        
        return paciente;
    }
    
}
