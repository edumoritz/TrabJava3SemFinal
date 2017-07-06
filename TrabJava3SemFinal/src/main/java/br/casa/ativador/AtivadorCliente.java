package br.casa.ativador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import br.casa.dao.ClasseDao;
import br.casa.pojo.Cliente;
import br.casa.tabelas.ClienteModel;
import br.casa.telas.TelaCliente;

public class AtivadorCliente extends TelaCliente{
	
	private Cliente clienteSelecionado;
	private ClienteModel tableModel;
	private ClasseDao dao;
	
	public AtivadorCliente() {
		super();
		clearFields();
		configureButtons();
		configureTable();
	}

	private void configureTable() {
		ClasseDao dao = new ClasseDao();
		List<Cliente> list = dao.getTodosC();
		
		this.tableModel = new ClienteModel(list);
		super.table.setModel(tableModel);
		super.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int idx = table.getSelectedRow();
					if(idx < 0){
						System.out.println("N�o h� linha selecionada");
					} else {
						loadRow(idx);
					}
				}
			}
		});
	}

	protected void loadRow(int idx) {
		Cliente ct = this.tableModel.getCliente(idx);
		this.clienteSelecionado = ct;
		this.lblCarregadoParaAlterao.setText(CARREGADO_PARA_ALTERACAO);
		super.txtId.setText(String.valueOf(ct.getId()));
		super.txtNome.setText(ct.getNome());
		super.txtTelefone.setText(ct.getTelefone());
		
		super.btnDeletar.setEnabled(true);
	}

	private void configureButtons() {
		super.btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				novoCliente();
			}
		});
		super.btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		super.btnDeletar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		
	}

	protected void deletar() {
		this.tableModel.remove(clienteSelecionado);
		dao = new ClasseDao();
		dao.excluiC(clienteSelecionado.getId());
		clearFields();
	}

	protected void salvar() {
		dao = new ClasseDao();
		if(clienteSelecionado == null){
			Cliente ct = new Cliente();
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strTelefone = txtTelefone.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			ct.setId(intId);
			ct.setNome(strNome);
			ct.setTelefone(strTelefone);
			
			this.tableModel.addCliente(ct);
			
			dao.insereC(ct);
			clearFields();
		} else {
			String strId = txtId.getText().trim();
			String strNome = txtNome.getText().trim();
			String strTelefone = txtTelefone.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			this.clienteSelecionado.setId(intId);
			this.clienteSelecionado.setNome(strNome);
			this.clienteSelecionado.setTelefone(strTelefone);
			
			dao.atualizaC(intId, clienteSelecionado);
			clearFields();
			this.tableModel.fireTableDataChanged();
		}
		
	}

	protected void novoCliente() {
		this.clienteSelecionado = null;
		clearFields();
	}

	private void clearFields() {
		super.lblCarregadoParaAlterao.setText("");
		super.txtId.setText("");
		super.txtNome.setText("");
		super.txtTelefone.setText("");
		
		super.btnDeletar.setEnabled(false);		
	}
	
	
}
