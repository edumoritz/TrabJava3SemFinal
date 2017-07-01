package br.casa.telas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelOrcamento extends JPanel {
	private JTextField textID;
	private JTable table;
	private JTextField textCliente;
	private JTextField textTelefone;

	public PainelOrcamento() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblIdf = new JLabel("Id(F2):");
		lblIdf.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblIdf = new GridBagConstraints();
		gbc_lblIdf.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdf.anchor = GridBagConstraints.EAST;
		gbc_lblIdf.gridx = 0;
		gbc_lblIdf.gridy = 0;
		panel_1.add(lblIdf, gbc_lblIdf);

		textID = new JTextField();
		GridBagConstraints gbc_textID = new GridBagConstraints();
		gbc_textID.insets = new Insets(0, 0, 5, 0);
		gbc_textID.anchor = GridBagConstraints.WEST;
		gbc_textID.gridx = 1;
		gbc_textID.gridy = 0;
		panel_1.add(textID, gbc_textID);
		textID.setColumns(5);
		
		JLabel lblCliente = new JLabel("Cliente:");
		GridBagConstraints gbc_lblCliente = new GridBagConstraints();
		gbc_lblCliente.anchor = GridBagConstraints.EAST;
		gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblCliente.gridx = 0;
		gbc_lblCliente.gridy = 1;
		panel_1.add(lblCliente, gbc_lblCliente);
		
		textCliente = new JTextField();
		GridBagConstraints gbc_textCliente = new GridBagConstraints();
		gbc_textCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCliente.insets = new Insets(0, 0, 5, 0);
		gbc_textCliente.gridx = 1;
		gbc_textCliente.gridy = 1;
		panel_1.add(textCliente, gbc_textCliente);
		textCliente.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		GridBagConstraints gbc_lblTelefone = new GridBagConstraints();
		gbc_lblTelefone.anchor = GridBagConstraints.EAST;
		gbc_lblTelefone.insets = new Insets(0, 0, 0, 5);
		gbc_lblTelefone.gridx = 0;
		gbc_lblTelefone.gridy = 2;
		panel_1.add(lblTelefone, gbc_lblTelefone);
		
		textTelefone = new JTextField();
		GridBagConstraints gbc_textTelefone = new GridBagConstraints();
		gbc_textTelefone.anchor = GridBagConstraints.WEST;
		gbc_textTelefone.gridx = 1;
		gbc_textTelefone.gridy = 2;
		panel_1.add(textTelefone, gbc_textTelefone);
		textTelefone.setColumns(15);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

}