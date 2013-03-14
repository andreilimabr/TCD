package br.com.andreilima.tcd.controle;

import br.com.andreilima.tcd.model.Usuario;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {
	private Result result;
	private SessaoUsuario sessao;
	
	public LoginController(Result result, SessaoUsuario sessao){
		this.result = result;
		this.sessao = sessao;
	}
	
	@Publica
	@Get("login")
	public void login(){
		
	}
	
	@Publica
	@Post("autenticar")
	public void	autenticar(Usuario usuario) {
		Usuario usuarioLogado = new ControlaAutenticacao().autenticar(usuario.getNome(), usuario.getSenha());
		if (usuarioLogado != null){
			this.sessao.setUsuario(usuarioLogado);
			this.result.redirectTo(IndiceController.class).indice();
		} else {
			this.result.include("erro",true);
			this.result.redirectTo(LoginController.class).login();
		}
	}
	
	@Get("logout")
	public void logout() {
		this.sessao.logout();
		result.redirectTo(this).login();
	}
	
	
}
