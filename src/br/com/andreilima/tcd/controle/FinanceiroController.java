package br.com.andreilima.tcd.controle;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.andreilima.tcd.dao.DAO;
import br.com.andreilima.tcd.model.Cliente;
import br.com.andreilima.tcd.model.MovimentacaoFinanceira;
import br.com.andreilima.tcd.util.JPAUtil;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class FinanceiroController {
	
	private Result result;
	
	public FinanceiroController(Result result){
		this.result = result;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Publica
	public void movimentacao(Cliente cliente) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			String jpql="select m " +
					" from MovimentacaoFinanceira m" +
					" left join m.pedido p" +
					" left join p.cliente c";
			Query query;
			
			if (cliente.getId()==null){
				query = em.createQuery(jpql);
			} else {
				jpql +=" where c.id = :id";
				query = em.createQuery(jpql);
				query.setParameter("id", cliente.getId());
			}
			List<MovimentacaoFinanceira> list = query.getResultList();
			this.result.include("listaMovimentacao",list );
			
			// lista de clientes
			jpql="select c from Cliente c ";
			Query query2 = em.createQuery(jpql);
			
			List<Cliente> list2 = query2.getResultList();
			this.result.include("listaCliente",list2);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			em.close();
		}
	}
	
	@Publica
	public void remove(MovimentacaoFinanceira mov) {
		EntityManager em = new JPAUtil().getEntityManager();
		try {
			DAO<MovimentacaoFinanceira> dao = new DAO<MovimentacaoFinanceira>(em,MovimentacaoFinanceira.class);
			dao.remove(mov.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		this.result.redirectTo(FinanceiroController.class).movimentacao(new Cliente());
	}
	
}
