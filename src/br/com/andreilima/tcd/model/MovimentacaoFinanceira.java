package br.com.andreilima.tcd.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="movimentacaofinanceira")
public class MovimentacaoFinanceira {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToOne(cascade=CascadeType.MERGE)
	private Pedido pedido;
	private double troco;
	@OneToOne(cascade=CascadeType.ALL)
	private CaixaGeral caixaGeral;
	private Calendar data;
	private double valorPago;
	
	
	public double getSoma() {
		return this.pedido.getTotal();
	}
	

	public double getTroco() {
		return troco;
	}

	public void setTroco(double troco) {
		this.troco = troco;
	}

	public CaixaGeral getCaixaGeral() {
		return caixaGeral;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valor) {
		double soma = this.getSoma();
		if(valor < soma){
			throw new RuntimeException("Valor pago informado � menor do que o valor total");
		}
		this.valorPago = valor;
		this.setTroco(valor - soma);
		this.getCaixaGeral().acrescenta(soma);
	}
	public void setCaixaGeral(CaixaGeral caixaGeral) {
		this.caixaGeral = caixaGeral;
		this.setData(this.getCaixaGeral().getData());
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
}
