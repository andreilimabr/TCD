package br.com.andreilima.tcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.model.Usuario;

public class ClienteDAO {
	private EntityManager entityManager;
	
	
	public ClienteDAO(EntityManager em){
		this.entityManager = em;
	}
	
	public void adiciona(Cliente c) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(c);
		this.entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getLista(){
		Query query = this.entityManager.createQuery("select c from Cliente c");
		List<Cliente> resultList = query.getResultList();
		return resultList;
	}
	
	public void  remove(Integer id) {
		this.entityManager.getTransaction().begin();
		Cliente cliente = this.entityManager.getReference(Cliente.class, id);
		this.entityManager.remove(cliente);
		this.entityManager.getTransaction().commit();
	}
	
	public Cliente busca(Integer id){
		Query query = this.entityManager.createQuery("select c from Cliente c where c.id =:id");
		query.setParameter("id", id);
		return (Cliente)query.getSingleResult();
	}
	public Cliente buscaPorUsuario(Usuario usuario){
		Query query = this.entityManager.createQuery("select c from Cliente c left join c.usuario u where u.id =:id");
		query.setParameter("id", usuario.getId());
		return (Cliente)query.getSingleResult();
	}
	
	public void altera(Cliente c) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(c);
		this.entityManager.getTransaction().commit();
	}
	
	
}
