package br.com.andreilima.tcd.controle;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Topico;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ForumController {
	private Result result;
	private SessaoUsuario sessao;
	
	public ForumController(Result result,SessaoUsuario sessao){
		this.result = result;
		this.sessao = sessao;
	}
	
	@SuppressWarnings("unchecked")
	@Path("forum/indice")
	public List<Topico> forum(List<Topico> lista) {
		this.result.include("usuario",this.sessao.getUsuario());
		if (lista == null){
			EntityManager em = new JPAUtil().getEntityManager();
			Query query = em.createQuery("select t from Topico t ");
			return (List<Topico>)query.getResultList();
		} else {
			return lista;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Post
	@Path("forum/busca")
	public void forumBusca(String topico) {
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery("select t from Topico t where t.titulo like :topico");
		query.setParameter("topico", "%" + topico + "%" );
		this.result.redirectTo(this).forum((List<Topico>)query.getResultList());
	}
	
	@Post
	@Path("forum/criar")
	public void forumCria(String topico) {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Topico> dao = new DAO<Topico>(em,Topico.class);
		Topico novoTopico = new Topico();
		novoTopico.setTitulo(topico);
		novoTopico.setUsuarioAbertura(this.sessao.getUsuario());
		novoTopico.setDataAbertura(Calendar.getInstance());
		dao.adiciona(novoTopico);
		this.result.redirectTo(this).forum(null);
	}
	
	

	@SuppressWarnings("unchecked")
	@Path("forum/discussao")
	public List<br.com.andreilima.tcd.model.Post> forumDiscussao(Topico topico) {
		EntityManager em = new JPAUtil().getEntityManager();
		Query query = em.createQuery("select p from Post p right outer join fetch p.topico t where p.topico.id = :id");
		query.setParameter("id", topico.getId());
		this.result.include("topico",topico);
		this.result.include("usuario",this.sessao.getUsuario());
		return (List<br.com.andreilima.tcd.model.Post>)query.getResultList();
	}
	
	@Post
	@Path("forum/postar")
	public void forumPostar(br.com.andreilima.tcd.model.Post post) {
		EntityManager em = new JPAUtil().getEntityManager();
		DAO<Topico> daoTopico = new DAO<Topico>(em,Topico.class);
		Topico topico = daoTopico.busca(post.getTopico().getId());
		DAO<br.com.andreilima.tcd.model.Post> dao = new DAO<br.com.andreilima.tcd.model.Post>(em,br.com.andreilima.tcd.model.Post.class);
		br.com.andreilima.tcd.model.Post novoPost = new br.com.andreilima.tcd.model.Post();
		novoPost.setPostagem(post.getPostagem());
		novoPost.setUsuarioPostagem(this.sessao.getUsuario());
		novoPost.setDataPostagem(Calendar.getInstance());
		novoPost.setTopico(topico);
		dao.adiciona(novoPost);
		this.result.include("topico",topico);
		this.result.include("usuario",this.sessao.getUsuario());
		this.result.redirectTo(this).forumDiscussao(topico);
	}
	
	
}
