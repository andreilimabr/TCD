package br.com.andreilima.tcd.controle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.ItemPedido;
import br.com.andreilima.tcd.model.Pedido;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PedidosController {
private Result result;
	
	public PedidosController(Result result){
		this.result = result;
	}
	
	

	public void adiciona(Pedido pedido){
		EntityManager em = new JPAUtil().getEntityManager();
		new DAO<Pedido>(em,Pedido.class).adiciona(pedido);
		em.close();
		//this.result.redirectTo(PedidosController.class).listaPedidos();
	}
	
	public List<Pedido> listaPedidos() {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Pedido> dao = new DAO<Pedido>(em,Pedido.class);
		return dao.getLista();
		
	}
	
	public void remove(Pedido pedido) {
		EntityManager em = new JPAUtil().getEntityManager();
		new DAO<Pedido>(em,Pedido.class).remove(pedido.getId());
		em.close();
		this.result.redirectTo(PedidosController.class).listaPedidos();
	}
	
	public Pedido alteraPedido(Pedido pedido){
		EntityManager em = new JPAUtil().getEntityManager();
		Pedido busca = new DAO<Pedido>(em,Pedido.class).busca(pedido.getId());
		return busca; // irá retornar um objeto de nome 'cliente'
	}
	
	public void altera(Pedido pedido) {
		EntityManager em = new JPAUtil().getEntityManager();
		new DAO<Pedido>(em,Pedido.class).altera(pedido);
		em.close();
		this.result.redirectTo(PedidosController.class).listaPedidos();
	}
	
	
	
	public void listaItensPedido(Pedido pedido) {	
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql="select i from ItemPedido i " +
					" right outer join fetch i.pedido p " +
					" where p.id =:pedido";
		Query query = em.createQuery(jpql);
		query.setParameter("pedido", pedido.getId());
		@SuppressWarnings("unchecked")
		List<ItemPedido> list = query.getResultList();
		this.result.include("itensPedido", list);
		
	}
	
}
