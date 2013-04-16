package br.com.andreilima.tcd.controle;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.andreilima.tcd.dao.ClienteDAO;
import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.model.Usuario;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ClientesController {
	
	private Result result;
	private SessaoUsuario sessao;
	
	public ClientesController(Result result, SessaoUsuario sessao){
		this.result = result;
		this.sessao = sessao;
	}
	
	@Publica
	@Path("/clientes/cadastro")
	public void cadastraCliente(Cliente cliente) {
		this.result.include("novoCliente", cliente);
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
	
	public void altera(Cliente cliente,String senhaAntiga, String senhaNova) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {	
			new ClienteDAO(em).altera(cliente);
			// altera os dados da senha de usuário
			DAO<Usuario> usuarioDAO = new DAO<Usuario>(em,Usuario.class);
			Usuario usuario = usuarioDAO.busca(cliente.getUsuario().getId());
			if (senhaAntiga != null){
				if(senhaAntiga.equals( usuario.getSenha())){
					usuario.setSenha(senhaNova);
					usuarioDAO.altera(usuario);
				} else{
					this.result.include("erro",true);
					this.result.forwardTo(ClientesController.class).alteraCliente(cliente);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		this.result.redirectTo(ClientesController.class).listaClientes();
	}
	
	@Path("compra/identificacao/cliente/")
	public void identificacao() {
		// pega o cliente, a partir do usuário
		EntityManager em = new JPAUtil().getEntityManager();
		Cliente cliente = new ClienteDAO(em).buscaPorUsuario(this.sessao.getUsuario());
		this.result.forwardTo(ClientesController.class).alteraCliente(cliente);
	}
	
	
}
