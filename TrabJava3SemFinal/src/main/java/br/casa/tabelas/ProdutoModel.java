package br.casa.tabelas;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import br.casa.pojo.Cliente;
import br.casa.pojo.Produto;

public class ProdutoModel extends AbstractTableModel {

	private List<Produto> lista;
	
	public ProdutoModel(List<Produto> list) {
		this.lista = list;
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
		switch(column){
		case 0:
			return "ID";
		case 1: 
			return "Descrição";
		case 2:
			return "Dolar";
		}
		return null;
	}

}
