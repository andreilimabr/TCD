package br.com.andreilima.tcd.testes;

import org.junit.Test;

import br.com.andreilima.tcd.controle.ControleDeEstoque;
import br.com.andreilima.tcd.controle.ControleVitrine;
import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.model.Estoque;
import br.com.andreilima.tcd.model.Produto;

public class TesteCompra {

	@Test
	public void CompraProdutoNormal() {
		// cliente
		Cliente cliente = new Cliente();
		
		// produtos
		Produto produto = new Produto();			
		Produto produto2 = new Produto();
		
		ControleDeEstoque estoque = new ControleDeEstoque();
		
		estoque.adiciona(new Estoque());
		estoque.adiciona(new Estoque());
		
		ControleVitrine vitrine = new ControleVitrine(estoque, null);
		cliente.getCarrinho().adiciona(vitrine.escolhe(1, 10));
		cliente.getCarrinho().adiciona(vitrine.escolhe(0, 15));
		
		
		
		
		
		
		
		
		
	}
	
	
	

}
