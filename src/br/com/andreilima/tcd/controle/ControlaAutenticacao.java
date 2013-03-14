package br.com.andreilima.tcd.controle;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.andreilima.tcd.model.Usuario;
import br.com.andreilima.tcd.util.JPAUtil;

public class ControlaAutenticacao {
	public Usuario autenticar(String nome, String senha) {
		EntityManager em = new JPAUtil().getEntityManager();
        try {
            Query query = em.createQuery("from Usuario where nome =:nome and senha = :senha");
            query.setParameter("nome", nome.toUpperCase());
            query.setParameter("senha", senha);
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally{
        	em.close();
        }
    }
}
