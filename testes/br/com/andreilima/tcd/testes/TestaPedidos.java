package br.com.andreilima.tcd.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.model.ItemPedido;
import br.com.andreilima.tcd.model.Pedido;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.model.StatusPedido;
import br.com.andreilima.tcd.util.JPAUtil;

public class TestaPedidos {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		try{
			
			/*Cliente cliente = new Cliente();
			cliente.setNome("Andrei Lima");
			cliente.setCpf("245245245");
			cliente.setEmail("andrei_lima_br@hotmail.com");
			cliente.setEndereco("rua paris, 274");
			*/
			Cliente cliente = new DAO<Cliente>(em,Cliente.class).busca(1);
			
			Pedido pedido = new Pedido();
			pedido.setData(Calendar.getInstance());
			pedido.setStatus(StatusPedido.ABERTO);
			pedido.setCliente(cliente);
			
			for (int i = 0; i < 11; i++) {
				int s=0;
				int q=0;
				while (s < 2 || s > 5){
					s = (int) (Math.random() * 5);
				}
				while (q < 1 || q > 10){
					q = (int) (Math.random() * 10);
				}
				ItemPedido item = new ItemPedido();
				Produto produto = em.getReference(Produto.class, s);
				item.setProduto(produto);
				item.setQtde(q);
				item.setUnitario(produto.getPreco());
				item.setTotal(produto.getPreco()* q);
				item.setPedido(pedido);
				pedido.adicionaItem(item);
				
			}
			
			em.persist(cliente);
			em.persist(pedido);
			
			em.getTransaction().commit();
		
		} catch(Exception e){
			e.printStackTrace();
			if ( em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
		} finally{
			em.close();
		}
	}
}
