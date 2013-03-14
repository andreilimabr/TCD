package br.com.andreilima.tcd.testes;

import org.junit.Test;

import br.com.andreilima.tcd.controle.ControleDeEstoque;
import br.com.andreilima.tcd.model.Produto;

public class ControleDeEstoqueTest {

	@Test(expected=IllegalArgumentException.class)
	public void estoqueNaoPodeserNulo() {
		ControleDeEstoque estoque = new ControleDeEstoque();
		estoque.adiciona(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void naoPodeRetornarQuantidadeSemEstoque() {
		ControleDeEstoque estoque = new ControleDeEstoque();
		estoque.getQuantidade(new Produto());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeRetornarQuantidadeSemProduto() {
		ControleDeEstoque estoque = new ControleDeEstoque();
		estoque.getQuantidade(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoPodeRetornarEstoqueSemProduto() {
		ControleDeEstoque estoque = new ControleDeEstoque();
		estoque.getEstoque(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void naoPodeRetornarEstoqueListaEstoqueVazia() {
		ControleDeEstoque estoque = new ControleDeEstoque();
		estoque.getEstoque(new Produto());
	}
	
	@Test(expected=RuntimeException.class)
	public void naoPodeRetornarEstoqueComProdutoInvalido() {
		ControleDeEstoque estoque = new ControleDeEstoque();
		estoque.getEstoque(new Produto());
	}
	
	

}
