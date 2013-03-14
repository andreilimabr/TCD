package br.com.andreilima.tcd.controle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Estoque;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class EstoqueController {
	
	private Result result;
	
	public EstoqueController(Result result){
		this.result = result;
	}
	
	@SuppressWarnings("unchecked")
	public void controlaEstoque() {
		EntityManager em = new JPAUtil().getEntityManager();
		// lista de estoques
		String jpql ="select e from Estoque e " +
					 " left join e.produto";
		Query query = em.createQuery(jpql);
		
		List<Estoque> estoque = query.getResultList();
		this.result.include("estoque",estoque);
		// inclui uma lista de produtos
		jpql ="select p from Produto p ";
		query = em.createQuery(jpql);
		
		List<Produto> produto = query.getResultList();
		result.include("produto",produto);
	}
	
	@SuppressWarnings("unchecked")
	public void adicionaProdutoEstoque(Estoque estoque) {
		EntityManager em = new JPAUtil().getEntityManager();
		// verifica a existencia do produto
		String jpql ="select p.titulo from Estoque e " +
				 	 " left join e.produto p " +
				 	 " where p.id =:produto";
		Query query = em.createQuery(jpql);
		query.setParameter("produto", estoque.getProduto().getId());
		List<Estoque> resultList = query.getResultList();
		if (resultList.size() > 0){
			this.result.notFound();
		} else {
			// cadastra produto no estoque
			DAO<Estoque> daoEstoque = new DAO<Estoque>(em,Estoque.class);
			estoque.setProduto(em.getReference(Produto.class, estoque.getProduto().getId()));
			daoEstoque.adiciona(estoque);
			this.result.include("estoque",daoEstoque.getLista());
			this.result.forwardTo(EstoqueController.class).listaItensEstoque();
		}
		em.close();
	}
	
	public void excluiEstoque(Estoque estoque) {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Estoque> dao = new DAO<Estoque>(em,Estoque.class);
		dao.remove(estoque.getId());
		this.result.include("estoque",dao.getLista());
		this.result.forwardTo(EstoqueController.class).listaItensEstoque();
		em.close();
	}
	
	
	public void listaItensEstoque() {
		
	}
}
