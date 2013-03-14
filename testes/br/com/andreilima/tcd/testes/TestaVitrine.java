package br.com.andreilima.tcd.testes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.andreilima.tcd.controle.ControleDeEstoque;
import br.com.andreilima.tcd.controle.ControleVitrine;
import br.com.andreilima.tcd.controle.SessaoUsuario;
import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.CategoriaVitrine;
import br.com.andreilima.tcd.model.ItemVitrine;
import br.com.andreilima.tcd.model.Produto;
import br.com.andreilima.tcd.model.Vitrine;
import br.com.andreilima.tcd.util.JPAUtil;

public class TestaVitrine {
	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			/* em.getTransaction().begin();
			// nova vitrine de oferta
			Vitrine vitrine = new Vitrine();
			vitrine.setCategoria(CategoriaVitrine.OFERTA);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 10);
			vitrine.setValidade(calendar);
			vitrine.setDescricao("Itens");
			
			//itens de vitrine
			// busca produtos
			DAO<Produto> dao = new DAO<Produto>(em,Produto.class);
			// lista de itens de vitrine
			List<ItemVitrine> lista = new ArrayList<>();
			for (Produto produto : dao.getLista()) {
				if (produto.getId() > 5 && produto.getId() <= 9) {
				ItemVitrine itemVitrine = new ItemVitrine();
				itemVitrine.setProduto(produto);
				itemVitrine.setPreco(produto.getPreco() -(produto.getPreco() * 0.1)); // dez purca de desconto
				itemVitrine.setVitrine(vitrine);
				// adiciona um produto a uma lista de Vitrine
				lista.add(itemVitrine);
				}
			}
			
			// adiciona a vitrine
			vitrine.setItens(lista);
			
			// salva 
			em.persist(vitrine);
			
			em.getTransaction().commit();
			*/
			
			ControleVitrine vitrine = new ControleVitrine(new ControleDeEstoque(), new SessaoUsuario());
			vitrine.atualizaMaisVendidos();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		
		
	}
}
