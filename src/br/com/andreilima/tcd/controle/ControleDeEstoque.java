package br.com.andreilima.tcd.controle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Estoque;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ControleDeEstoque {
	
	
	
	public void adiciona(Estoque estoque) {
		if (estoque==null){
			throw new IllegalArgumentException("Estoque não pode ser nulo.");
		}
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Estoque> dao = new DAO<Estoque>(em,Estoque.class);
		dao.adiciona(estoque);
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Estoque> getListaEstoque() {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql =" select e From Estoque e ";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
	public int getQuantidade(Produto p) {
		if (p==null){
			throw new IllegalArgumentException("Produto não pode ser nulo");
		}
		// consulta mais otimizada
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql =" select e.qtd from Estoque e" +
					 " left join e.produto p" +
					 " where p.id =:produto";
		Query query = em.createQuery(jpql);
		query.setParameter("produto", p.getId());
		int resultado =(int)query.getSingleResult();
		em.close();
		return resultado;
	}
	
	public Estoque getEstoque(Produto p) {
		Estoque estoque=null;
		if (p==null){
			throw new IllegalArgumentException("Produto não pode ser nulo");
		}
		if (this.getListaEstoque().size()==0){
			throw new NullPointerException("Lista de produtos vazia");
		}
		
		// otimizando a consulta
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql ="select e From Estoque e" +
					 " left join e.produto p" +
					 " where p.id =:produto";
		Query query = em.createQuery(jpql);
		query.setParameter("produto", p.getId());
		estoque = (Estoque) query.getSingleResult();
		em.close();
		if (estoque==null){
			throw new RuntimeException("Produto não encontrado na lista");
		} else {
			return estoque;
		}	
	}
	
	public List<Produto> listaProdutos() {
		List<Produto> lista = new ArrayList<Produto>();
		for (Estoque e : this.getListaEstoque()) {
			lista.add(e.getProduto());
		}
		return lista;
	}
	
	public Produto baixaItem(Produto produto,int qtd) {
		Estoque e = this.getEstoque(produto);
		if ((e.getQtd()-qtd) >= e.getQtdMin() ){
			// baixa a quantidade do estoque cadastrado
			
			EntityManager em = new JPAUtil().getEntityManager();
			try{
				em.getTransaction().begin();
				String jpql="update Estoque e " +
							" set e.qtd=e.qtd-:qtd" +
							" where e.id=:id";
				Query query = em.createQuery(jpql);
				query.setParameter("qtd", qtd);
				query.setParameter("id", e.getId());
				query.executeUpdate();
				em.getTransaction().commit();
			} catch (Exception ex){
				em.getTransaction().rollback();
				ex.printStackTrace();
			} finally {
				em.close();
			}
			return e.getProduto(); 
		} else {
			throw new RuntimeException("Quantidade de estoque insuficiente");
		}
	}

}
