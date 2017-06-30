package planodecontas;

import java.math.BigDecimal;

public class Conta {
	
	private int id;
	private String descricao;
	private BigDecimal valor;
	
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
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return "id: "+this.id+" descricao: "+this.descricao+" valor: "+this.valor;
	}
	

}
