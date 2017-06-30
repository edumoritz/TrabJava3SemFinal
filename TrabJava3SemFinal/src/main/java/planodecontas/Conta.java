package planodecontas;

import java.math.BigDecimal;
import java.util.List;

public class Conta {
	
	private int id;
	private String codigo;
	private List<Conta> filhas;
	private int levelId;
	private BigDecimal valor;

	// GETTERS/SETTERS

	public int getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public List<Conta> getFilhas() {
		return filhas;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setFilhas(List<Conta> filhas) {
		this.filhas = filhas;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValor() {
		return valor;
	}
	

}
