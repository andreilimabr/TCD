package br.com.andreilima.tcd.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="caixaggeral")
public class CaixaGeral {
	@Id
	@GeneratedValue
	private Integer id;
	private Calendar data;
	private double valor;
	
	
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void acrescenta(double valor) {
		this.valor += valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
