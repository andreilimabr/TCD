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
	private UsuarioController usuarioControler;
	
	public ClientesController(Result result, SessaoUsuario sessao,UsuarioController usuarioControler){
		this.result = result;
		this.sessao = sessao;
		this.usuarioControler = usuarioControler;
	}
	
	@Publica
	@Path("/clientes/cadastro")
	public void cadastraCliente(Cliente cliente) {
		this.result.include("cliente", cliente);
	}
	

	@Publica
	public void adiciona(Cliente cliente, String confirmaSenha){
			Usuario usuario = cliente.getUsuario();
			EntityManager em = new JPAUtil().getEntityManager();
			try {
				// checa o cadastro
				checaCadastro(cliente,confirmaSenha);
				// adiciona o usuario
				usuario.setNome(cliente.getNome());
				usuario.setAdministrador(false);
				this.usuarioControler.adiciona(usuario);
				new ClienteDAO(em).adiciona(cliente);
				this.result.redirectTo(CarrinhoController.class).carrinho();
			} catch (RuntimeException e) {
				this.result.include("msgErro",e.getMessage());
				this.result.forwardTo(this).cadastraCliente(cliente);
			} finally{
				em.close();
			}
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
	
	private void checaCadastro(Cliente cliente, String confirmaSenha) throws RuntimeException {
		
		if (cliente.getNome()== null){
			throw new RuntimeException("Por favor informe seu nome de usuário.");
		}
		if (cliente.getEndereco()== null){
			throw new RuntimeException("Por favor informe seu endereco.");
		}
		if (cliente.getCpf()== null){
			throw new RuntimeException("Por favor informe seu cpf.");
		}
		if (cliente.getEmail()== null){
			throw new RuntimeException("Por favor informe seu E-Mail.");
		}
		if (cliente.getCep()== null){
			throw new RuntimeException("Por favor informe seu Cep.");
		}
		// senhas
		if (cliente.getUsuario().getSenha() == null){
			throw new RuntimeException("Por favor, informe uma senha válida.");
		}
		if (confirmaSenha == null){
			throw new RuntimeException("Por favor, confirme a sua senha.");
		}
		if ( ! cliente.getUsuario().getSenha().equals(confirmaSenha)){
			throw new RuntimeException("As senhas não conferem. por favor, tente novamente.");
		}
		
	}
	
}
