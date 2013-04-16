package br.com.andreilima.tcd.interceptor;

import javax.servlet.http.HttpServletRequest;

import br.com.andreilima.tcd.controle.LoginController;
import br.com.andreilima.tcd.controle.Publica;
import br.com.andreilima.tcd.controle.SessaoUsuario;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class LoginInterceptor implements Interceptor {
	
	private Result result;
	private SessaoUsuario sessao;
	private HttpServletRequest request;
	
	public LoginInterceptor(Result result,SessaoUsuario sessao, HttpServletRequest request){
		this.result = result;
		this.sessao =sessao;
		this.request = request;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		// TODO Auto-generated method stub
		return 
				! (method.getMethod().isAnnotationPresent(Publica.class) ||
				method.getResource().getType().isAnnotationPresent(Publica.class));
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		// TODO Auto-generated method stub
		if (this.sessao.isLogado()){
			stack.next(method, resourceInstance);
		} else {
			String uri = this.request.getRequestURI().replaceFirst(this.request.getContextPath(), "");
			result.include("uriTo",uri);
			result.redirectTo(LoginController.class).loginCliente();
		}
	}

}
