package br.com.andreilima.tcd.controle;

import javax.persistence.EntityManager;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.ItemPedido;
import br.com.andreilima.tcd.model.MovimentacaoFinanceira;
import br.com.andreilima.tcd.model.Pedido;
import br.com.andreilima.tcd.model.StatusPedido;
import br.com.andreilima.tcd.util.JPAUtil;

public class ControleFaturamento {
	public void realizaVendaDireta(MovimentacaoFinanceira movimentacao) {
		// pega o pedido
		Pedido pedido = movimentacao.getPedido();
		// adiciona pedido,  com status de pago
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Pedido> dao = new DAO<Pedido>(em,Pedido.class);
		pedido.setStatus(StatusPedido.ENTREGUE);
		dao.adiciona(pedido);
		
		// adiciona a movimentacao
		DAO<MovimentacaoFinanceira> daoMov = new DAO<MovimentacaoFinanceira>(em,MovimentacaoFinanceira.class);
		daoMov.adiciona(movimentacao);
	
		// faz a  baixa no estoque
		ControleDeEstoque estoque = new ControleDeEstoque();
		for (ItemPedido item : pedido.getItens()) {
			estoque.baixaItem(item.getProduto(), item.getQtde());
		}
		
		
	}
	
	
}
