package br.com.andreilima.tcd.controle;

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
	
	public LoginInterceptor(Result result,SessaoUsuario sessao){
		this.result = result;
		this.sessao =sessao;
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
			result.redirectTo(LoginController.class).login();
		}
	}

}
