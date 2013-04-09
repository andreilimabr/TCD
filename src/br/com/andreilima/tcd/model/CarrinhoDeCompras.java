package br.com.andreilima.tcd.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CarrinhoDeCompras {
	private Integer id;
	private List<ItemCarrinhoCompras> itens;
	private Cliente cliente;
	private double total;

	public CarrinhoDeCompras() {
		this.itens = new ArrayList<ItemCarrinhoCompras>();
	}
	public List<ItemCarrinhoCompras> getItens() {
		return this.itens;
	}
	public void adiciona(ItemCarrinhoCompras item) {
		this.itens.add(item);
		this.total +=(item.getPreco() * item.getQtde());
	}
	
	public void remove(int item) {
		ItemCarrinhoCompras it = this.getItens().get(item);
		this.total -=(it.getPreco() * it.getQtde());
		this.itens.remove(item);
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

    public boolean isProduto(Integer id) {
		for (ItemCarrinhoCompras item : this.itens) {
			if (item.getProduto().getId() == id){
				return true;
			}
		}
		return false;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}


}
