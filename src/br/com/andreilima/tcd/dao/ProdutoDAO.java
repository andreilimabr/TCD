package br.com.andreilima.tcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.model.Produto;

public class ProdutoDAO {
private EntityManager entityManager;
	
	
	public ProdutoDAO(EntityManager em){
		this.entityManager = em;
	}
	
	public void adiciona(Produto p) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(p);
		this.entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> getLista(){
		Query query = this.entityManager.createQuery("select p from Produto p");
		List<Produto> resultList = query.getResultList();
		return resultList;
	}
	
	public void  remove(Integer id) {
		this.entityManager.getTransaction().begin();
		Produto produto = this.entityManager.getReference(Produto.class, id);
		this.entityManager.remove(produto);
		this.entityManager.getTransaction().commit();
	}
	
	public Produto busca(Integer id){
		Query query = this.entityManager.createQuery("select p from Produto p where p.id =:id");
		query.setParameter("id", id);
		return (Produto)query.getSingleResult();
	}
	
	public void altera(Produto p) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(p);
		this.entityManager.getTransaction().commit();
	}
}
