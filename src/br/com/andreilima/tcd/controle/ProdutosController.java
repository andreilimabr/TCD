package br.com.andreilima.tcd.controle;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.ItemVitrine;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ProdutosController {
	private Result result;
	
	public ProdutosController(Result result){
		this.result = result;
	}
	
	public void cadastraProduto() {
		
	}
	

	public void adiciona(Produto produto){
		EntityManager em = new JPAUtil().getEntityManager();
		new DAO<Produto>(em,Produto.class).adiciona(produto);
		em.close();
		this.result.redirectTo(ProdutosController.class).listaProdutos();
	}
	
	public List<Produto> listaProdutos() {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Produto> dao = new DAO<Produto>(em,Produto.class);
		return dao.getLista();
		
	}
	@Publica
	@SuppressWarnings("unchecked")
	@Get("/produtos/busca/{crit}")
	public List<ItemVitrine> listaPesquisa(String crit/*,int comeco,int tamanho*/){
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			int tamanho = 5;
			int comeco =0;
			// tamanho incial padrao, se nao foi informado
			if (tamanho >=0){
				tamanho=5;
			}
			if (comeco <= 0){
				comeco =0;
			} else {
				comeco +=(tamanho-1);
			}
			if (crit != null) {
				// numero de linhas retornadas
				String jpq ="select count(i.produto) from ItemVitrine i" +
						" left join i.produto p" +
						" where p.titulo like :crit";
				Query qryItens = em.createQuery(jpq);
				qryItens.setParameter("crit","%" + crit.toLowerCase() + "%");
				Long numeroLinhas = (Long)qryItens.getSingleResult();
				Long numeroPaginas = numeroLinhas /tamanho;
	
				jpq ="select i from ItemVitrine i" +
						" left join i.produto p" +
						" where p.titulo like :crit";
				Query query = em.createQuery(jpq);
				query.setMaxResults(tamanho);
				query.setFirstResult(comeco);
				query.setParameter("crit","%" + crit.toLowerCase() + "%");
				if (numeroPaginas > 0){
					this.result.include("numeroPaginas",numeroPaginas);
					this.result.include("criterio", crit);
				}
				return query.getResultList();
			} else {
				return new ArrayList<>();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
	
	public void remove(Produto produto) {
		EntityManager em = new JPAUtil().getEntityManager();
		new DAO<Produto>(em,Produto.class).remove(produto.getId());
		em.close();
		this.result.redirectTo(ProdutosController.class).listaProdutos();
	}
	
	public Produto alteraProduto(Produto produto){
		EntityManager em = new JPAUtil().getEntityManager();
		Produto busca = new DAO<Produto>(em,Produto.class).busca(produto.getId());
		return busca; // irá retornar um objeto de nome 'cliente'
	}
	
	public void altera(Produto produto) {
		EntityManager em = new JPAUtil().getEntityManager();
		new DAO<Produto>(em,Produto.class).altera(produto);
		em.close();
		this.result.redirectTo(ProdutosController.class).listaProdutos();
	}
	
	
	
}
