package br.com.andreilima.tcd.controle;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.andreilima.tcd.dao.ClienteDAO;
import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ClientesController {
	
	private Result result;
	
	public ClientesController(Result result){
		this.result = result;
	}
	
	public void cadastraCliente() {
		
	}
	
	public void adiciona(Cliente cliente){
		EntityManager em = new JPAUtil().getEntityManager();
		new ClienteDAO(em).adiciona(cliente);
		em.close();
		this.result.redirectTo(ClientesController.class).listaClientes();
	}
	
	public List<Cliente> listaClientes() {
		EntityManager em = new JPAUtil().getEntityManager();
		ClienteDAO dao = new ClienteDAO(em);
		return dao.getLista();
		
	}
	
	public void remove(Cliente cliente) {
		EntityManager em = new JPAUtil().getEntityManager();
		new ClienteDAO(em).remove(cliente.getId());
		em.close();
		this.result.redirectTo(ClientesController.class).listaClientes();
	}
	
	public Cliente alteraCliente(Cliente cliente){
		EntityManager em = new JPAUtil().getEntityManager();
		Cliente busca = new ClienteDAO(em).busca(cliente.getId());
		return busca; // irá retornar um objeto de nome 'cliente'
	}
	
	public void altera(Cliente cliente) {
		EntityManager em = new JPAUtil().getEntityManager();
		new ClienteDAO(em).altera(cliente);
		em.close();
		this.result.redirectTo(ClientesController.class).listaClientes();
	}
	
	
}
