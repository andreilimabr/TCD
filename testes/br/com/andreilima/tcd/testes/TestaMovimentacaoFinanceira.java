package br.com.andreilima.tcd.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.andreilima.tcd.controle.ControleFaturamento;
import br.com.andreilima.tcd.model.MovimentacaoFinanceira;
import br.com.andreilima.tcd.model.CaixaGeral;
import br.com.andreilima.tcd.model.CarrinhoDeCompras;
import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.model.ItemCarrinhoCompras;
import br.com.andreilima.tcd.model.ItemPedido;
import br.com.andreilima.tcd.model.Pedido;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.model.StatusPedido;
import br.com.andreilima.tcd.util.JPAUtil;

public class TestaMovimentacaoFinanceira {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		// pego um cliente
		Cliente cliente = em.getReference(Cliente.class, 30); // Andrei Lima
		
		// simulando várias compras
		//crio o meu carrinho de compras
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		cliente.setCarrinho(carrinho);
		
		//escolho os produtos
		Produto produto1 = em.getReference(Produto.class, 2);
		Produto produto2 = em.getReference(Produto.class, 3);
		
		//transformo os produtos em itens de carrinho
		ItemCarrinhoCompras item1 = new ItemCarrinhoCompras();
		item1.setProduto(produto1);
		item1.setQtde(2);
		ItemCarrinhoCompras item2 = new ItemCarrinhoCompras();
		item2.setProduto(produto2);
		item2.setQtde(5);
		
		// adiciona no carrinho
		cliente.getCarrinho().adiciona(item1);
		cliente.getCarrinho().adiciona(item2);
		
		// transforma o carrinho em pedido e os itens
		// em itens de pedido
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setData(Calendar.getInstance());
		pedido.setStatus(StatusPedido.ABERTO);
		for (ItemCarrinhoCompras item : carrinho.getItens()) {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setProduto(item.getProduto());
			itemPedido.setQtde(item.getQtde());
			itemPedido.setUnitario(item.getProduto().getPreco());
			itemPedido.setTotal(item.getQtde() * item.getProduto().getPreco());
			itemPedido.setPedido(pedido);
			pedido.adicionaItem(itemPedido);
		}
		
		// vai para o caixa e paga a compra
		// abre o caixa de hoje
		CaixaGeral caixaGeral = new CaixaGeral();
		caixaGeral.setData(Calendar.getInstance());
		
		// abre o caixa para fazer a operação
		MovimentacaoFinanceira caixa = new MovimentacaoFinanceira();
		caixa.setPedido(pedido);
		caixa.setCaixaGeral(caixaGeral);
		System.out.println("Total...:" + caixa.getSoma());		
		
		//pago com R$ 300,00
		caixa.setValorPago(300.00);
		// me devolve o troco
		double troco = caixa.getTroco();
		System.out.println("Pago....:300");
		System.out.println("Troco...:" + troco);
		//mostra o valor do caixa geral e dá um bom dia ;)
		System.out.println("==================");
		System.out.println("Total Caixa Geral:" + caixaGeral.getValor());
		System.out.println("Tenha um ótimo dia");
		
		//gera toda a movimentacao
		new ControleFaturamento().realizaVendaDireta(caixa);
		
		em.close();
		
	}
}
