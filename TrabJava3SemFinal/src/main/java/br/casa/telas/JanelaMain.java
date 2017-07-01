package br.casa.telas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class JanelaMain extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnCrateTable;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaMain frame = new JanelaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
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
		GridBagConstraints gbc_btnCrateTable = new GridBagConstraints();
		gbc_btnCrateTable.anchor = GridBagConstraints.EAST;
		gbc_btnCrateTable.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrateTable.gridx = 0;
		gbc_btnCrateTable.gridy = 0;
		contentPane.add(btnCrateTable, gbc_btnCrateTable);
		GridBagConstraints gbc_btnCarregar = new GridBagConstraints();
		gbc_btnCarregar.anchor = GridBagConstraints.WEST;
		gbc_btnCarregar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCarregar.gridx = 1;
		gbc_btnCarregar.gridy = 0;
		contentPane.add(btnCarregar, gbc_btnCarregar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
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
			
		}
		
	}

}
