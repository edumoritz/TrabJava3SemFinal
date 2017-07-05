package br.casa.tabelas;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import br.casa.pojo.Produto;
import br.casa.pojo.ProdutoOrc;

public class OrcamentoModel extends AbstractTableModel {

	private List<ProdutoOrc> lista;
	

	public void preencherResultado(List<ProdutoOrc> result){
		this.lista = result;
		fireTableDataChanged();
	}
	public OrcamentoModel(List<ProdutoOrc> list) {
		this.lista = list;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int row, int column) {
		ProdutoOrc p = this.lista.get(row);

		switch (column) {
		case 0:
			return p.getId();
		case 1:
			return p.getDescricao();
		case 2:
			return p.getQuantidade();
		case 3:
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
			return "QTD";
		case 3:
			return "VALOR";
		}
		return null;
	}

	public ProdutoOrc getProdutoAt(int idx){
		if(idx >= this.lista.size()){
			return null;
		}
		return this.lista.get(idx);
	}
}
