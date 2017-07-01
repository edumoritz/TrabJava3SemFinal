package br.casa.telacadastro;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteModel extends AbstractTableModel {

	private List<Cliente> lista;

	public ClienteModel() {
		this((List<Cliente>) null);
	}

	public ClienteModel(List<Cliente> list) {
		if (list == null) {
			this.lista = new ArrayList<>();
		} else {
			this.lista = list;
		}
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
		Cliente ct = this.lista.get(row);

		switch (column) {
		case 0:
			return ct.getId();
		case 1:
			return ct.getNome();
		case 2:
			return ct.getTelefone();
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {

		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "NOME";
		case 2:
			return "TELEFONE";
		}
		return null;
	}

	public void addCliente(Cliente c) {
		this.lista.add(c);
		super.fireTableDataChanged();
	}

	public Cliente getCliente(int idx) {
		return lista.get(idx);
	}

	public void remove(Cliente clienteSelecionado) {
		this.lista.remove(clienteSelecionado);
		super.fireTableDataChanged();
	}

	public Cliente getClienteAt(int idx) {
		if (idx >= this.lista.size()) {
			return null;
		}
		return this.lista.get(idx);
	}

}
