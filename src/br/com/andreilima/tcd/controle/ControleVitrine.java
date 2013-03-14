package br.com.andreilima.tcd.controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.model.CategoriaVitrine;
import br.com.andreilima.tcd.model.ItemCarrinhoCompras;
import br.com.andreilima.tcd.model.ItemPedido;
import br.com.andreilima.tcd.model.ItemVitrine;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.model.Vitrine;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.ioc.Component;
@Component
public class ControleVitrine {
	private ControleDeEstoque estoque;
	private SessaoUsuario sessao;
	
	public ControleVitrine(ControleDeEstoque estoque,SessaoUsuario sessao){
		this.estoque = estoque;
		this.sessao = sessao;
	}

	public List<Produto> getMostruario() {
		return this.estoque.listaProdutos();
	}
	
	public ItemCarrinhoCompras escolhe(int item,int qtd) {
		Produto p =this.getMostruario().get(item);
		ItemCarrinhoCompras itemCarrinho = new ItemCarrinhoCompras();
		itemCarrinho.setProduto(p);
		itemCarrinho.setQtde(qtd);
		return itemCarrinho;
	}
	
	private boolean isCarrinho(Integer id){
		return this.sessao.getCarrinho().isProduto(id);
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemVitrine> getItensVitrine(Vitrine vitrine){
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			// cria a query
			Query query;
			
			// jpql básico
			String jpql =" select i " +
					 " from ItemVitrine i" +	
					 " left join i.produto p" +
					 " left join i.vitrine v";
			// se id nao for nulo, filtra pelo id
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
			
			return list;
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		 
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public void atualizaMaisVendidos() {
		EntityManager em = new JPAUtil().getEntityManager();
		try{
			// exclui uma lista anterior, para ser atualizada
			em.getTransaction().begin();
			String jpql =" delete from Vitrine v where v.categoria='MAIS'";
			Query qryDelete = em.createQuery(jpql);
			qryDelete.executeUpdate();
			em.getTransaction().commit();
			
			em.getTransaction().begin();
			// retorna uma lista dos dez produtos mais vendidos
			jpql="select i from ItemPedido i" +
						" group by i.produto" +
						" order by sum(i.qtde)";
			Query query = em.createQuery(jpql);
			List<ItemPedido> list = query.setMaxResults(10).getResultList();
			// interage com a lista recebida e 
			// cria os itens de vitrine
			Vitrine vitrine = new Vitrine();
			vitrine.setCategoria(CategoriaVitrine.MAIS);
			vitrine.setDescricao("Mais vendidos");
			vitrine.setValidade(Calendar.getInstance());
			List<ItemVitrine> listaItens = new ArrayList<>();
			for (ItemPedido item : list) {
				ItemVitrine itemVitrine = new ItemVitrine();
				itemVitrine.setProduto(item.getProduto());
				itemVitrine.setPreco(item.getProduto().getPreco());
				itemVitrine.setVitrine(vitrine);
				listaItens.add(itemVitrine);
			}
			vitrine.setItens(listaItens);
			em.persist(vitrine);
			em.getTransaction().commit();
		} catch (Exception e){
			if (em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			em.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<ItemVitrine> getMaisVendidos() {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			String jpql="select i from ItemVitrine i left join i.vitrine v where v.categoria='MAIS'";
			Query query = em.createQuery(jpql);
			List<ItemVitrine> list = query.getResultList();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}
	
	
}
