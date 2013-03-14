package br.com.andreilima.tcd.model;

import java.util.Calendar;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class MovimentacaoResult {
	private double troco;
	private double valorPago;
	private Calendar data;
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	private double total;
	private String nome;
	public double getTroco() {
		return troco;
	}
	public void setTroco(double troco) {
		this.troco = troco;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public StatusPedido getStatus() {
		return status;
	}
	public void setStatus(StatusPedido status) {
		this.status = status;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
