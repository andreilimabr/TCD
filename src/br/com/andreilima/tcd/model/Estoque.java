package br.com.andreilima.tcd.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estoque {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne(fetch=FetchType.LAZY)
	private Produto produto;
	private int qtd;
	private int qtdMin;
	
	

	public int getQtd() {
		return qtd;
	}


	public int getQtdMin() {
		return qtdMin;
	}

	public void setQtdMin(int qtdMin) {
		this.qtdMin = qtdMin;
	}

	public Produto getProduto() {
		return this.produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public void setQtd(int qtd) {
		this.qtd = qtd;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
}
