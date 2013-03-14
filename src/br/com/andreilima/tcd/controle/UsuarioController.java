package br.com.andreilima.tcd.controle;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Usuario;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class UsuarioController {
private Result result;
	
	public UsuarioController(Result result){
		this.result = result;
	}
	
	public void cadastraUsuario() {
		
	}
	
	public void adiciona(Usuario usuario){
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Usuario> dao = new DAO<Usuario>(em,Usuario.class);
		String jpql="select u.id from Usuario u " +
					" where u.nome =:nome";
		Query query = em.createQuery(jpql);
		query.setParameter("nome", usuario.getNome().toUpperCase());
		if (query.getResultList().size()==0) {
			usuario.setNome(usuario.getNome().toUpperCase());
			dao.adiciona(usuario);
			this.result.use(Results.status()).ok();
		} else {
			this.result.notFound();
		}
		em.close();
	}
}
