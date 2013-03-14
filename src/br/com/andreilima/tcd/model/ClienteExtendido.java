package br.com.andreilima.tcd.model;


public class ClienteExtendido {
	private Cliente cliente = new Cliente();
	public ClienteExtendido(Integer id, String nome){
		this.cliente.setId(id);
		this.cliente.setNome(nome);
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
