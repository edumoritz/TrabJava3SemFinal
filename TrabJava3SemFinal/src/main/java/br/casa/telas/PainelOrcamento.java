package br.casa.telas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import br.casa.dao.ClasseDao;
import br.casa.dao.UtilSql;
import br.casa.pojo.Cliente;
import br.casa.pojo.Produto;
import br.casa.tabelas.OrcamentoModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PainelOrcamento extends JPanel {
	private JTextField textID;
	private JTable table;
	private JTextField textCliente;
	private JTextField textTelefone;
	private OrcamentoModel orcModel;

	public PainelOrcamento() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 349, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 107, 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblIdf = new JLabel("Id:");
		lblIdf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblIdf = new GridBagConstraints();
		gbc_lblIdf.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdf.anchor = GridBagConstraints.EAST;
		gbc_lblIdf.gridx = 0;
		gbc_lblIdf.gridy = 0;
		panel_1.add(lblIdf, gbc_lblIdf);

		textID = new JTextField();
		textID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F2) {
					abreBusca();
				}
			}
		});
		GridBagConstraints gbc_textID = new GridBagConstraints();
		gbc_textID.insets = new Insets(0, 0, 5, 5);
		gbc_textID.anchor = GridBagConstraints.WEST;
		gbc_textID.gridx = 1;
		gbc_textID.gridy = 0;
		panel_1.add(textID, gbc_textID);
		textID.setColumns(5);

		JLabel lblDigiteFPara = new JLabel("Digite F2 para filtrar Cliente");
		lblDigiteFPara.setForeground(Color.GRAY);
		GridBagConstraints gbc_lblDigiteFPara = new GridBagConstraints();
		gbc_lblDigiteFPara.anchor = GridBagConstraints.WEST;
		gbc_lblDigiteFPara.insets = new Insets(0, 0, 5, 5);
		gbc_lblDigiteFPara.gridx = 2;
		gbc_lblDigiteFPara.gridy = 0;
		panel_1.add(lblDigiteFPara, gbc_lblDigiteFPara);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 1;
		panel_1.add(lblCliente, gbc_lblCliente);

		textCliente = new JTextField();
		GridBagConstraints gbc_textCliente = new GridBagConstraints();
		gbc_textCliente.gridwidth = 3;
		gbc_textCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCliente.insets = new Insets(0, 0, 5, 5);
		gbc_textCliente.gridx = 1;
		gbc_textCliente.gridy = 1;
		panel_1.add(textCliente, gbc_textCliente);
		textCliente.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 0, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 2;
		panel_1.add(lblTelefone, gbc_lblTelefone);

		textTelefone = new JTextField();
		GridBagConstraints gbc_textTelefone = new GridBagConstraints();
		gbc_textTelefone.insets = new Insets(0, 0, 0, 5);
		gbc_textTelefone.anchor = GridBagConstraints.WEST;
		gbc_textTelefone.gridx = 1;
		gbc_textTelefone.gridy = 2;
		panel_1.add(textTelefone, gbc_textTelefone);
		textTelefone.setColumns(15);

		JButton btnInserirProduto = new JButton("Inserir Produto");
		btnInserirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreBuscaProduto();
			}
		});
		GridBagConstraints gbc_btnInserirProduto = new GridBagConstraints();
		gbc_btnInserirProduto.insets = new Insets(0, 0, 0, 5);
		gbc_btnInserirProduto.anchor = GridBagConstraints.EAST;
		gbc_btnInserirProduto.gridx = 2;
		gbc_btnInserirProduto.gridy = 2;
		panel_1.add(btnInserirProduto, gbc_btnInserirProduto);

		JButton btnDeletarProduto = new JButton("Deletar Produto");
		btnDeletarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropProduto();
			}
		});
		GridBagConstraints gbc_btnDeletarProduto = new GridBagConstraints();
		gbc_btnDeletarProduto.anchor = GridBagConstraints.WEST;
		gbc_btnDeletarProduto.gridx = 3;
		gbc_btnDeletarProduto.gridy = 2;
		panel_1.add(btnDeletarProduto, gbc_btnDeletarProduto);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
		GridBagConstraints gbc_lblTotal = new GridBagConstraints();
		gbc_lblTotal.anchor = GridBagConstraints.EAST;
		gbc_lblTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblTotal.gridx = 0;
		gbc_lblTotal.gridy = 3;
		add(lblTotal, gbc_lblTotal);

		JLabel lblResultado = new JLabel("resultado");
		GridBagConstraints gbc_lblResultado = new GridBagConstraints();
		gbc_lblResultado.gridx = 1;
		gbc_lblResultado.gridy = 3;
		add(lblResultado, gbc_lblResultado);
	}
//	Estou tentando pegar o valor id de produto de uma linha para dropar na tabela e no banco.
	protected void dropProduto() {
//		UtilSql us = new UtilSql();
//		int idx = (int) table.getValueAt(table.getSelectedRow(), 0);
//		us.dropRow(idx);
//		
//		System.out.println(idx);
//		table.setModel(orcModel);
//		this.orcModel.fireTableDataChanged();

	}

	protected void abreBuscaProduto() {
		PainelBuscaProduto buscaProd = new PainelBuscaProduto();

		buscaProd.setOnOk(new Consumer<Produto>() {

			@Override
			public void accept(Produto t) {
				preencherProd(t);
			}
		});
		buscaProd.setOnCancel(new Runnable() {

			@Override
			public void run() {
				limparCampos();

			}
		});
		buscaProd.setVisible(true);

	}

	protected void preencherProd(Produto t) {
		UtilSql us = new UtilSql();
		us.insertSqlProd(t);
		List<Produto> list = us.getTodosP();
		this.orcModel = new OrcamentoModel(list);
		table.setModel(orcModel);
		this.orcModel.fireTableDataChanged();
	}

	protected void abreBusca() {
		PainelBusca painelBusca = new PainelBusca();

		painelBusca.setOnOk(new Consumer<Cliente>() {

			@Override
			public void accept(Cliente t) {
				preencher(t);
			}
		});

		painelBusca.setOnCancel(new Runnable() {

			@Override
			public void run() {
				limparCampos();
			}
		});
		painelBusca.setVisible(true);
	}

	protected void limparCampos() {
		textID.setText("");
		textCliente.setText("");
		textTelefone.setText("");
	}

	protected void preencher(Cliente t) {
		textID.setText(String.valueOf(t.getId()));
		textCliente.setText(t.getNome());
		textTelefone.setText(t.getTelefone());
	}

}
