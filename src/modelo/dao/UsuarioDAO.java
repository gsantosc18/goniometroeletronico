/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.security.NoSuchAlgorithmException;
import modelo.entity.Usuario;
import modelo.util.EncryptMD5;
import modelo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author myhouse
 */
public class UsuarioDAO implements Entity{
    @Override
    public void add(Object o) {
        Usuario user = (Usuario) o;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        session.save(user);
        transacao.commit();
    }

    @Override
    public void update(Object o) {
        Usuario user = (Usuario) o;        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        session.update(user);
        transacao.commit();        
    }

    @Override
    public void delete(Object o) {
        Usuario user = (Usuario) o;        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        session.delete(user);
        transacao.commit();
    }

    public Usuario login(String email, String senha) throws NoSuchAlgorithmException {
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        Usuario usuario = (Usuario) session.createQuery("from Usuario where email=? and senha=?")
                .setParameter(0, email)
                .setParameter(1, EncryptMD5.encrypt(senha))
                .uniqueResult();
        transacao.commit();
        
        return usuario;
    }
    
    public Usuario findByEmail(String email){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transacao = session.beginTransaction();
        Usuario usuario = (Usuario) session.createQuery("from Usuario where email=?")
                .setParameter(0, email)
                .uniqueResult();
        transacao.commit();
        
        return usuario;
    }
    
}
