package br.com.andreilima.tcd.model;

public class ProdutoVitrineView implements ProdutoVenda {
	private String titulo;
	private double preco;
	private boolean noCarrinho;
	private Integer id;
	private Vitrine vitrine;
	private Produto produto;
	
	public ProdutoVitrineView(String titulo, double preco, int noCarrinho,Integer id,Vitrine vitrine, Produto produto){
		this.titulo = titulo;
		this.preco = preco;
		this.noCarrinho =  noCarrinho==0 ? false : true;
		this.id = id;
		this.vitrine = vitrine;
		this.produto = produto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public boolean isNoCarrinho() {
		return noCarrinho;
	}

	public void setNoCarrinho(boolean noCarrinho) {
		this.noCarrinho = noCarrinho;
	}

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

	public Vitrine getVitrine() {
		return vitrine;
	}

	public void setVitrine(Vitrine vitrine) {
		this.vitrine = vitrine;
	}

	
	
	
}
