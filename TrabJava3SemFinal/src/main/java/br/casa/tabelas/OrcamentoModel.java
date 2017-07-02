package br.casa.tabelas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.casa.pojo.Produto;

public class OrcamentoModel extends AbstractTableModel {

	private List<Produto> lista = new ArrayList<>();
	BigDecimal dolar;
	int qtd;

	public void preencherResultado(List<Produto> result){
		this.lista = result;
		fireTableDataChanged();
	}
	public OrcamentoModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Produto p = this.lista.get(row);

		switch (column) {
		case 0:
			return p.getId();
		case 1:
			return p.getDescricao();
		case 2:
			return p.getValorDolar();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "DESCRICAO";
		case 2:
			return "VALOR";
		}
		return null;
	}

	public Produto getProdutoAt(int idx){
		if(idx >= this.lista.size()){
			return null;
		}
		return this.lista.get(idx);
	}
}
