package br.com.andreilima.tcd.controle;

import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.model.Usuario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {
	private Result result;
	private SessaoUsuario sessao;
	private ClientesController clientesController;
	
	public LoginController(Result result, SessaoUsuario sessao, ClientesController clientesController){
		this.result = result;
		this.sessao = sessao;
		this.clientesController = clientesController;
	}
	
	
	@Publica
	@Get("login")
	public void loginCliente(){
		
	}
	
	@Publica
	@Post("autenticar")
	public void	autenticar(Usuario usuario, String uriTo) {
		Usuario usuarioLogado = new ControlaAutenticacao().autenticar(usuario.getNome(), usuario.getSenha());
		if (usuarioLogado != null){
			this.sessao.setUsuario(usuarioLogado);
			//this.result.redirectTo(IndiceController.class).indice();
			Cliente clienteSessao = this.clientesController.getClienteSessao();
			this.result.include("cliente",clienteSessao);
			this.result.redirectTo(uriTo);
			
		} else {
			this.result.include("erro",true);
			this.result.redirectTo(LoginController.class).loginCliente();
			
		}
	}
	
	@Get("logout")
	public void logout() {
		this.sessao.logout();
		result.redirectTo(this).loginCliente();
	}
	
	
}
