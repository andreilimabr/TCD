package br.com.andreilima.tcd.controle;

import br.com.andreilima.tcd.model.CarrinhoDeCompras;
import br.com.andreilima.tcd.model.Usuario;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
@Component
@SessionScoped
public class SessaoUsuario {
	private Usuario usuario;
	private CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
	
	public boolean isLogado() {
		return this.usuario != null;
	}
	
	public void logout(){
		this.usuario =null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoDeCompras carrinho) {
		this.carrinho = carrinho;
	}
}
