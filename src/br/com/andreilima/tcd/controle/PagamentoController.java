package br.com.andreilima.tcd.controle;

import java.util.Calendar;
import java.util.List;

import br.com.andreilima.tcd.model.ItemCarrinhoCompras;
import br.com.andreilima.tcd.model.ItemPedido;
import br.com.andreilima.tcd.model.Pedido;
import br.com.andreilima.tcd.model.StatusPedido;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PagamentoController {
	private Result result;
	private SessaoUsuario sessao;
	private ClientesController clientesController;
	private PedidosController pedidosController;
	
	public PagamentoController(Result result, SessaoUsuario sessao, 
			ClientesController clientesController, PedidosController pedidosContoller){
		this.result = result;
		this.sessao = sessao;
		this.clientesController = clientesController;
		this.pedidosController = pedidosContoller;
	}
	
	@Path("compra/pagamento")
	public List<ItemCarrinhoCompras> pagamento() {
		List<ItemCarrinhoCompras> itens = this.sessao.getCarrinho().getItens();
		return itens;
	}
	
	public void geraPedido() {
		Pedido pedido = new Pedido();
		List<ItemCarrinhoCompras> itens = this.sessao.getCarrinho().getItens();
		for (ItemCarrinhoCompras itemCarrinhoCompras : itens) {
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setProduto(itemCarrinhoCompras.getProduto());
			itemPedido.setQtde(itemCarrinhoCompras.getQtde());
			itemPedido.setUnitario(itemCarrinhoCompras.getPreco());
			itemPedido.setTotal(itemCarrinhoCompras.getPreco() * itemCarrinhoCompras.getQtde());
			pedido.adicionaItem(itemPedido);
		}
		pedido.setTotal(this.sessao.getCarrinho().getTotal());
		pedido.setStatus(StatusPedido.ANALISE);
		pedido.setData(Calendar.getInstance());
		pedido.setCliente(this.clientesController.getClienteSessao());
		this.pedidosController.adiciona(pedido);
		
	}
	
}
