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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Table(name="pedido")
@Component
public class Pedido {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Cliente cliente;
	private Calendar data;
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="pedido")
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	private double total;
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<ItemPedido> getItens() {
		return itens;
	} 
	
	public void adicionaItem(ItemPedido item) {
		if (item.getTotal()> 0 ){
			this.itens.add(item);
			this.total+=item.getTotal();
		}
	}
	public void removeItem(int indice) {
		this.total-=this.itens.get(indice).getTotal();
		this.itens.remove(indice);
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
}
