package br.com.andreilima.tcd.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Topico topico;
	@OneToOne
	private Usuario usuarioPostagem;
	private Calendar dataPostagem;
	private String postagem;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	public Usuario getUsuarioPostagem() {
		return usuarioPostagem;
	}
	public void setUsuarioPostagem(Usuario usuario) {
		this.usuarioPostagem = usuario;
	}
	public Calendar getDataPostagem() {
		return dataPostagem;
	}
	public void setDataPostagem(Calendar dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
	public String getPostagem() {
		return postagem;
	}
	public void setPostagem(String postagem) {
		this.postagem = postagem;
	}
}
