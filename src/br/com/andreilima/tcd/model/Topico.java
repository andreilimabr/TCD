package br.com.andreilima.tcd.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Topico {
@Id	
@GeneratedValue
private int id;
private String titulo;
@OneToOne
private Usuario usuarioAbertura;
private Calendar dataAbertura;
@OneToMany(mappedBy="topico", cascade=CascadeType.ALL)
private List<Post> posts;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public Usuario getUsuarioAbertura() {
	return usuarioAbertura;
}
public void setUsuarioAbertura(Usuario usuarioAbertura) {
	this.usuarioAbertura = usuarioAbertura;
}
public Calendar getDataAbertura() {
	return dataAbertura;
}
public void setDataAbertura(Calendar dataAbertura) {
	this.dataAbertura = dataAbertura;
}
public List<Post> getPosts() {
	return posts;
}
public void setPosts(List<Post> posts) {
	this.posts = posts;
}
}
