package br.casa.telas;

import java.math.BigDecimal;

public class Produto {

	private Long id;
	private String descricao;
	private BigDecimal valorDolar;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	
	@Override
	public String toString() {
		return "id: "+this.id+" descrição: "+this.descricao+" valor: "+this.valorDolar;
	}
}
