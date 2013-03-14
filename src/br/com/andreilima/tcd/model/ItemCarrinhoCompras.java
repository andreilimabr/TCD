package br.com.andreilima.tcd.model;



public class ItemCarrinhoCompras implements ProdutoVenda {

	private Integer id;

	private Produto produto;
	private int qtde;
	private double preco; //pode estar em oferta
	private CarrinhoDeCompras carrinho;
	
	public Produto getProduto() {
		return produto;
	}

	public int getQtde() {
		return qtde;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoDeCompras carrinho) {
		this.carrinho = carrinho;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	
}
