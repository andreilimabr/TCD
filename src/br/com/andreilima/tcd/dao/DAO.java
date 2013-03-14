package br.com.andreilima.tcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class DAO<T> {
	private final EntityManager entityManager;
	private final Class<T> classe;
	
	public DAO(EntityManager em,Class<T> classe){
		this.entityManager = em;
		this.classe =classe;
	}
	
	public void adiciona(T obj) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(obj);
		this.entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getLista(){
		Query query = this.entityManager.createQuery("select c from " + this.classe.getName() + " c");
		List<T> resultList = query.getResultList();
		return resultList;
	}
	
	public void  remove(Integer id) {
		this.entityManager.getTransaction().begin();
		T obj = this.entityManager.getReference(this.classe, id);
		this.entityManager.remove(obj);
		this.entityManager.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public T busca(Integer id){
		Query query = this.entityManager.createQuery("select c from " + this.classe.getName() + " c where c.id =:id");
		query.setParameter("id", id);
		return (T)query.getSingleResult();
	}
	
	public void altera(T obj) {
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(obj);
		this.entityManager.getTransaction().commit();
	}

}
