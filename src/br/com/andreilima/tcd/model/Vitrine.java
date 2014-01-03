package br.com.andreilima.tcd.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vitrine")
public class Vitrine {
	@Id
	@GeneratedValue
	private Integer id;
	@Enumerated(value=EnumType.STRING)
	private CategoriaVitrine categoria;
	private Calendar validade;
	@OneToMany(mappedBy="vitrine",cascade=CascadeType.ALL)
	private List<ItemVitrine> itens = new ArrayList<>();
	private String descricao;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Calendar getValidade() {
		return validade;
	}
	public void setValidade(Calendar validade) {
		this.validade = validade;
	}
	public List<ItemVitrine> getItens() {
		return itens;
	}
	public void setItens(List<ItemVitrine> itens) {
		this.itens = itens;
	}
	public CategoriaVitrine getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaVitrine categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
