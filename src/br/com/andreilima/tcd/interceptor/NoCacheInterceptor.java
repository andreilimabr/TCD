package br.com.andreilima.tcd.interceptor;

import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
@RequestScoped
public class NoCacheInterceptor implements Interceptor {
	
	private final HttpServletResponse response;
	
	public NoCacheInterceptor(HttpServletResponse res){
		this.response = res;
	}
	
	@Override
	public boolean accepts(ResourceMethod method) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object resourceInstance) throws InterceptionException {
		// TODO Auto-generated method stub
		this.response.setHeader("Expires", "Wed, 31 Dec 1969 21:00:00 GMT");
		this.response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		this.response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		this.response.addHeader("Pragma", "no-cache");
		
		stack.next(method, resourceInstance);
	}

}
