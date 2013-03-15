package br.com.andreilima.tcd.controle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.model.ItemCarrinhoCompras;
import br.com.andreilima.tcd.model.ItemVitrine;
import br.com.andreilima.tcd.model.Vitrine;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class VitrineController {
	
	private Result result;
	private SessaoUsuario sessao;
	
	public VitrineController(Result result, SessaoUsuario sessao){
		this.result = result;
		this.sessao = sessao;
	}
	
	@SuppressWarnings("unchecked")
	@Publica
	public void visualiza(Vitrine vitrine){
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// cria a query
			Query query;
			
			// jpql básico
			String jpql =" select i " +
					 " from ItemVitrine i" +	
					 " left join i.produto p" +
					 " left join i.vitrine v";
			// se categoria nao for nula, filtra pela categoria
			if (vitrine.getCategoria() != null){
				
				jpql += " where v.categoria =:categoria";
				query = em.createQuery(jpql);
				query.setParameter("categoria", vitrine.getCategoria());
				
			} else {
				query = em.createQuery(jpql);
			}
			
			List<ItemVitrine> list = query.getResultList();
			
			// verifica se os produtos estão no carrinho
			for (ItemVitrine item : list) {
				if (this.isCarrinho(item.getProduto().getId())){
					item.setNoCarrinho(true);
				}
			}
			
			this.result.include("vitrineList",list);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		
		 
	}
	
	
	
	@Publica
	public void comprar(ItemVitrine item) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			String jpql =" select i " +
						 " from ItemVitrine i" +	
						 " left join i.produto p" +
						 " left join i.vitrine v" +
						 " where i.id = :id";
			Query query = em.createQuery(jpql);
			query.setParameter("id", item.getId());
			item = (ItemVitrine) query.getResultList().get(0);
			this.adicionaNoCarrinho(item);
			this.result.forwardTo(VitrineController.class).visualiza(item.getVitrine());
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			em.close();
		}
	}
	
	private boolean adicionaNoCarrinho(ItemVitrine itemVitrine){
		if (itemVitrine != null) {
			ItemCarrinhoCompras item = new ItemCarrinhoCompras();
			item.setProduto(itemVitrine.getProduto());
			item.setPreco(itemVitrine.getPreco());
			item.setQtde(1);
			item.setId(this.sessao.getCarrinho().getItens().size());
			this.sessao.getCarrinho().adiciona(item);
			return true;
		} 
		return false;
	}
	
	private boolean isCarrinho(Integer id){
		return this.sessao.getCarrinho().isProduto(id);
	}
	
	
	
}
