package br.casa.pojo;

import java.math.BigDecimal;

public class Produto {

	private int id;
	private String descricao;
	private BigDecimal valorDolar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorDolar() {
		return valorDolar;
	}
	public void setValorDolar(BigDecimal valorDolar) {
		this.valorDolar = valorDolar;
	}
	
//	@Override
//	public String toString() {
//		return this.id+", "+this.descricao+", "+this.valorDolar;
//	}
}
