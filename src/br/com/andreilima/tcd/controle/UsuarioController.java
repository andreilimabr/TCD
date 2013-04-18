package br.com.andreilima.tcd.controle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Usuario;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Resource;

@Resource
public class UsuarioController {

	
	public void cadastraUsuario() {
		
	}
	
	public void adiciona(Usuario usuario) throws RuntimeException{
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Usuario> dao = new DAO<Usuario>(em,Usuario.class);
		String jpql="select u.id from Usuario u " +
					" where u.nome =:nome";
		Query query = em.createQuery(jpql);
		query.setParameter("nome", usuario.getNome().toUpperCase());
		
		Integer id = null;
		try {
			id = (Integer)query.getSingleResult();
			usuario.setId(id);
		} catch (Exception e) {
			usuario.setNome(usuario.getNome().toUpperCase());
			dao.adiciona(usuario);
		} finally{
			if (id == null){
				id = (Integer)query.getSingleResult();
				usuario.setId(id);
			} else {
				throw new RuntimeException("Nome de usuário já existe, por favor informe outro nome.");
			}	
			em.close();
		}
		
		
	}
}
