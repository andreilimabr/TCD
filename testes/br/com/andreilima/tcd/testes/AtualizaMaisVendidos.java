package br.com.andreilima.tcd.testes;

import br.com.andreilima.tcd.controle.ControleDeEstoque;
import br.com.andreilima.tcd.controle.ControleVitrine;
import br.com.andreilima.tcd.controle.SessaoUsuario;

public class AtualizaMaisVendidos {
	public static void main(String[] args) {
		ControleVitrine controleVitrine = new ControleVitrine(new ControleDeEstoque(), new SessaoUsuario());
		controleVitrine.atualizaMaisVendidos();
	}
}
