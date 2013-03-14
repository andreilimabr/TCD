package br.com.andreilima.tcd.model;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CarrinhoDeCompras {
	private Integer id;
	private List<ItemCarrinhoCompras> itens;
	private Cliente cliente;

	public CarrinhoDeCompras() {
		this.itens = new ArrayList<ItemCarrinhoCompras>();
	}
	public List<ItemCarrinhoCompras> getItens() {
		return this.itens;
	}
	public void adiciona(ItemCarrinhoCompras item) {
		this.itens.add(item);
	}
	
	public void remove(int item) {
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


}
