package br.com.andreilima.tcd.controle;

import java.util.List;

import br.com.andreilima.tcd.model.CarrinhoDeCompras;
import br.com.andreilima.tcd.model.ItemCarrinhoCompras;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class CarrinhoController {
	private SessaoUsuario sessao;
	private Result result;
		
	public CarrinhoController(SessaoUsuario ses, Result res){
		this.sessao = ses;
		this.result = res;
	}
	
	@Publica
	@Get("carrinho")
	public List<ItemCarrinhoCompras> carrinho(){
		CarrinhoDeCompras carrinho =this.sessao.getCarrinho();
		return carrinho.getItens();
	}
	
	@Publica
	@Path("carrinho/remove/item/{item.id}")
	@Delete
	public void removeItemCarrinho(ItemCarrinhoCompras item){
		this.removePorId(item);
		this.result.nothing();
	}
	
	private void removePorId(ItemCarrinhoCompras item){
		int indice=0;
		CarrinhoDeCompras carrinho = this.sessao.getCarrinho();
		List<ItemCarrinhoCompras> itens = this.sessao.getCarrinho().getItens();
		double total = carrinho.getTotal();
		for (ItemCarrinhoCompras i : itens ) {
			if (item.getId()==i.getId()){
				total -=(i.getPreco() * i.getQtde());
				carrinho.setTotal(total);
				itens.remove(indice);
				break;
			}
			indice++;
		}
	}
	
	@Publica
	@Get
	@Path("carrinho/total")
	public void total(){
		this.result.use(Results.json()).from(this.sessao.getCarrinho()).serialize();
		
	}
}
