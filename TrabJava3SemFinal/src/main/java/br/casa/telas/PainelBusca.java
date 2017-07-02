package br.casa.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.casa.dao.ClasseDao;
import br.casa.pojo.Cliente;
import br.casa.pojo.Produto;
import br.casa.tabelas.ClienteModel;
import br.casa.tabelas.OrcamentoModel;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

public class PainelBusca extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private Consumer<Cliente> consumerOnOk;
	private Runnable runnableOnCancel;
	private ClienteModel tableModelC;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelBusca frame = new PainelBusca();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PainelBusca() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 0);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		// $hide>>$
		configuraTela();
		// $hide<<$
	}

	private void configuraTela() {
		ClienteModel model = new ClienteModel();
		table.setModel(model);

		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					busca(textField.getText().trim());
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					table.getSelectionModel().setSelectionInterval(0, 0);
					textField.transferFocus();
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (PainelBusca.this.runnableOnCancel != null) {
						PainelBusca.this.runnableOnCancel.run();
					}
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					table.transferFocusBackward();
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					int idx = table.getSelectedRow();
					if (idx != -1) {
						Cliente pt = ((ClienteModel) table.getModel()).getClienteAt(idx);
						if (pt == null) {
							return;
						}
						PainelBusca.this.consumerOnOk.accept(pt);
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (PainelBusca.this.runnableOnCancel != null) {
						PainelBusca.this.runnableOnCancel.run();
					}
				}
			}
		});
	}

	protected void busca(String palavra) {
		ClasseDao dao = new ClasseDao();
		if(palavra.isEmpty()){
			List<Cliente> listaB = dao.getTodosC();
			this.tableModelC = new ClienteModel(listaB);
			table.setModel(tableModelC);

		} else {
			List<Cliente> listaB = dao.filterCliente(palavra);
			this.tableModelC = new ClienteModel(listaB);
			table.setModel(tableModelC);
		}

	}

	public void setOnOk(Consumer<Cliente> c) {
		this.consumerOnOk = c;
	}

	public void setOnCancel(Runnable r) {
		this.runnableOnCancel = r;
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				textField.requestFocusInWindow();
			}
		});
	}

}
