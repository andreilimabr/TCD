package br.com.andreilima.tcd.controle;

import java.util.List;

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
	
	@SuppressWarnings("unchecked")
	public void adiciona(Usuario usuario){
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Usuario> dao = new DAO<Usuario>(em,Usuario.class);
		String jpql="select u.id from Usuario u " +
					" where u.nome =:nome";
		Query query = em.createQuery(jpql);
		query.setParameter("nome", usuario.getNome().toUpperCase());
		List<Integer> lista = query.getResultList();
		if (lista.size()==0) {
			usuario.setNome(usuario.getNome().toUpperCase());
			dao.adiciona(usuario);
			lista = query.getResultList();
			usuario.setId(lista.get(0));
			this.result.use(Results.status()).ok();
		} else {
			usuario.setId(lista.get(0));
			this.result.use(Results.status()).accepted();
		}
		em.close();
	}
}
