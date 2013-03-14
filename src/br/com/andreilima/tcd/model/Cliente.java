package br.com.andreilima.tcd.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import br.com.caelum.vraptor.ioc.Component;

@Entity
@Component
public class Cliente {
	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String endereco;
	private String email;
	private String cpf;
	@Transient
	private CarrinhoDeCompras carrinho;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="cliente")
	private List<Pedido> pedidos;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCarrinho(CarrinhoDeCompras carrinho) {
		this.carrinho = carrinho;
	}
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	public void adicionaPedido(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
}
