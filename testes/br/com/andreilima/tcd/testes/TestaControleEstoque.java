package br.com.andreilima.tcd.testes;

import javax.persistence.EntityManager;

import br.com.andreilima.tcd.controle.ControleDeEstoque;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.util.JPAUtil;

public class TestaControleEstoque {
	public static void main(String[] args) {
		ControleDeEstoque estoque = new ControleDeEstoque();
		// pega o produto
		EntityManager em = new JPAUtil().getEntityManager();
		Produto produto = em.getReference(Produto.class, 2); // CD do metallica \m/
		// estoque antes da baixa
		System.out.println("Estoque desse produto antes da baixa..:" + estoque.getQuantidade(produto));
		estoque.baixaItem(produto, 2);
		// estoque depois da baixa
		System.out.println("Estoque desse produto depois da baixa..:" + estoque.getQuantidade(produto));
		em.close();
	}
}
