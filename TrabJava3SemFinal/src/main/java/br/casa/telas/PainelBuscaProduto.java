package br.casa.telas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import br.casa.pojo.Produto;
import br.casa.tabelas.ProdutoModel;
import java.awt.Insets;
import java.util.function.Consumer;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelBuscaProduto extends JFrame {
	private JTextField textField;
	private JTable table;
	private Consumer<Produto> consumerOnOk;
	private Runnable runnableOnCancel;
	private ProdutoModel prodModel;

	public PainelBuscaProduto() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblDescrio = new GridBagConstraints();
		gbc_lblDescrio.insets = new Insets(0, 0, 5, 0);
		gbc_lblDescrio.gridx = 0;
		gbc_lblDescrio.gridy = 0;
		getContentPane().add(lblDescrio, gbc_lblDescrio);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		getContentPane().add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		// $hide>>$
		configuraTela();
		// $hide<<$
	}

	private void configuraTela() {
		//ProdutoModel model = new ProdutoModel(list);
		
		
		
		
	}

}
