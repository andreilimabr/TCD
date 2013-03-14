package br.com.andreilima.tcd.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ItemPedido {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Pedido pedido;
	@OneToOne(cascade=CascadeType.MERGE)
	private Produto produto;
	private double unitario;
	private Integer	qtde;
	private double total;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getUnitario() {
		return unitario;
	}
	public void setUnitario(double unitario) {
		this.unitario = unitario;
	}
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
