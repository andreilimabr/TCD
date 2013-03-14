package br.com.andreilima.tcd.testes;

import org.junit.Test;

import br.com.andreilima.tcd.controle.ControleDeEstoque;
import br.com.andreilima.tcd.controle.ControleVitrine;
import br.com.andreilima.tcd.controle.SessaoUsuario;

public class VitrineTest {

	
	@Test
	public void atualizaListaMaisVendidos() {
		ControleVitrine controleVitrine = new ControleVitrine(new ControleDeEstoque(), new SessaoUsuario());
		controleVitrine.atualizaMaisVendidos();
	}

}
