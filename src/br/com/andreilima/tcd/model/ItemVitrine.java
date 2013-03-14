package br.com.andreilima.tcd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import br.com.caelum.vraptor.ioc.Component;

@Component
@Entity
public class ItemVitrine implements ProdutoVenda {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne(cascade=CascadeType.MERGE)
	private Produto produto;
	private double preco;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Vitrine vitrine;
	@Transient
	private boolean noCarrinho;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public Vitrine getVitrine() {
		return vitrine;
	}
	public void setVitrine(Vitrine vitrine) {
		this.vitrine = vitrine;
	}
	public boolean isNoCarrinho() {
		return noCarrinho;
	}
	public void setNoCarrinho(boolean noCarrinho) {
		this.noCarrinho = noCarrinho;
	}
}
