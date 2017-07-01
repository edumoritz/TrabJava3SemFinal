package br.casa.telas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JTable;

import br.casa.ativador.LeitorUrl;
import br.casa.dao.UtilSql;
import br.casa.pojo.Produto;
import br.casa.tabelas.ProdutoModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class JanelaLista extends JPanel {


	private JTable table;
	private JButton btnCrateTable;
	private JPanel panel;

	public JanelaLista() {

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gbl_contentPane);
		
		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregar();
			}
		});
		
		btnCrateTable = new JButton("CriarTabela\r\n");
		btnCrateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable();
			}
		});
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagConstraints gbc_btnCrateTable = new GridBagConstraints();
		gbc_btnCrateTable.anchor = GridBagConstraints.EAST;
		gbc_btnCrateTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrateTable.gridx = 0;
		gbc_btnCrateTable.gridy = 1;
		add(btnCrateTable, gbc_btnCrateTable);
		GridBagConstraints gbc_btnCarregar = new GridBagConstraints();
		gbc_btnCarregar.anchor = GridBagConstraints.WEST;
		gbc_btnCarregar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCarregar.gridx = 1;
		gbc_btnCarregar.gridy = 1;
		add(btnCarregar, gbc_btnCarregar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

	protected void createTable() {
		UtilSql us = new UtilSql();
		us.createTableSql();
	}

	protected void carregar() {
		String url = "http://www.master10.com.py/lista-txt/download";
		LeitorUrl lu = new LeitorUrl();
		
		try {
			List<Produto> list = lu.lerProdutos(url);
			ProdutoModel model = new ProdutoModel(list);
			table.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
