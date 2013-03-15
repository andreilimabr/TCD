package br.com.andreilima.tcd.controle;

import java.util.List;

import br.com.andreilima.tcd.model.CarrinhoDeCompras;
import br.com.andreilima.tcd.model.ItemCarrinhoCompras;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

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
	@Path("carrinho/remove/item/{item}")
	public void removeItemCarrinho(int item){
		CarrinhoDeCompras carrinho = this.sessao.getCarrinho();
		carrinho.getItens().remove(item);
		this.result.redirectTo(CarrinhoController.class).carrinho();
	}
}
