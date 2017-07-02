package br.casa.telas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.casa.dao.ClasseDao;
import br.casa.pojo.Produto;
import br.casa.tabelas.ProdutoModel;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelBuscaProduto extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private Consumer<Produto> consumerOnOk;
	private Runnable runnableOnCancel;
	private ProdutoModel prodModel;

	public PainelBuscaProduto() {
		setBounds(700, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNome = new JLabel("Descrição");
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
		ClasseDao dao = new ClasseDao();
		List<Produto> list = dao.getTodosP();
		ProdutoModel model = new ProdutoModel(list);
		table.setModel(model);
		
		textField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					busca(textField.getText().trim());
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN){
					table.getSelectionModel().setSelectionInterval(0, 0);
					textField.transferFocus();
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(PainelBuscaProduto.this.runnableOnCancel != null){
						PainelBuscaProduto.this.runnableOnCancel.run();
					}
				}
			}
		});
		table.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP){
					table.transferFocusBackward();
				}
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					e.consume();
					int idx = table.getSelectedRow();
					if(idx != -1){
						Produto pt = ((ProdutoModel)table.getModel()).getProdutoAt(idx);
						if(pt == null){
							return;
						}
						PainelBuscaProduto.this.consumerOnOk.accept(pt);
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(PainelBuscaProduto.this.runnableOnCancel != null){
						PainelBuscaProduto.this.runnableOnCancel.run();
					}
				}
			}
		});
		
		
	}
	
	protected void busca(String trim) {
		ClasseDao dao = new ClasseDao();
		if(trim.isEmpty()){
			List<Produto> list = dao.getTodosP();
		} else {
			List<Produto> list = dao.filterProduto(trim);
			this.prodModel = new ProdutoModel(list);
			table.setModel(prodModel);
		}
	}

	public void setOnOk(Consumer<Produto> c) {
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
