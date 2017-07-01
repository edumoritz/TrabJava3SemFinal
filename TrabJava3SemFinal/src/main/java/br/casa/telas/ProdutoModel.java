package br.casa.telas;

import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProdutoModel extends AbstractTableModel {

	private List<Produto> lista;
	private BigDecimal valor;

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
		Produto p = this.lista.get(row);

		switch (column) {
		case 0:
			return p.getId();
		case 1:
			return p.getDescricao();
		case 2:
			return p.getValorDolar();
		case 3:
			return p.getValorDolar().multiply(this.valor);
		}
		return null;
	}

}
