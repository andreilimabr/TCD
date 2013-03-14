package br.com.andreilima.tcd.controle;

import java.util.List;

import br.com.andreilima.tcd.model.CategoriaVitrine;
import br.com.andreilima.tcd.model.ItemVitrine;
import br.com.andreilima.tcd.model.Vitrine;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndiceController {
	
		private Result result;
		private ControleVitrine controleVitrine;
		private SessaoUsuario sessao;
		
		public IndiceController(Result result,ControleVitrine controleVitrine,SessaoUsuario sessao){
			this.result = result;
			this.controleVitrine = controleVitrine;
			this.sessao = sessao;
		}
	
		
		@Publica
	    @Get("indice")
	    public void indice() {
			Vitrine vitrine = new Vitrine();
			// destaques
			vitrine.setCategoria(CategoriaVitrine.DESTAQUE);
			List<ItemVitrine> itemVitrine = this.controleVitrine.getItensVitrine(vitrine);
			this.result.include("destaqueList",itemVitrine);
			// novo
			vitrine.setCategoria(CategoriaVitrine.NOVO);
			List<ItemVitrine> itemVitrine2 = this.controleVitrine.getItensVitrine(vitrine);
			this.result.include("novoList",itemVitrine2);
			// ofertas
			vitrine.setCategoria(CategoriaVitrine.OFERTA);
			List<ItemVitrine> itemVitrine3 = this.controleVitrine.getItensVitrine(vitrine);
			this.result.include("ofertasList",itemVitrine3);
			// mais vendidos
			List<ItemVitrine> itemVitrine4 = this.controleVitrine.getMaisVendidos();
			this.result.include("vendidosList",itemVitrine4);
			// usuário logado
			if (this.sessao.isLogado()){
				this.result.include("usuarioLogado"," Olá," + this.sessao.getUsuario().getNome() + "! Seja Bem Vindo!");
			} else {
				this.result.include("usuarioLogado","");
			}
	    }
		
		@Publica
		public void destaques() {
			
		}
		
		@Publica
		public void comprar(ItemVitrine item) {
			this.result.forwardTo(VitrineController.class).comprar(item);
			
		}
}
