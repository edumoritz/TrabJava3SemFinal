package br.casa.telas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.casa.dao.ClasseDao;
import br.casa.pojo.Produto;
import br.casa.pojo.ProdutoOrc;
import br.casa.tabelas.OrcamentoModel;
import br.casa.tabelas.ProdutoModel;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
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
	private Consumer<ProdutoOrc> consumerOnOkB;
	private Runnable runnableOnCancel;
	private ProdutoModel prodModel;
	private OrcamentoModel orcModel;

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

		JLabel lblNome = new JLabel("Descri��o");
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
					ProdutoOrc pol;
					pol = loadRow(idx);//FUN��O PEGA VALORES DAS CEDULAS DE UMA LINHA SELECIONADA
					PainelBuscaProduto.this.consumerOnOkB.accept(pol);
				}
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					if(PainelBuscaProduto.this.runnableOnCancel != null){
						PainelBuscaProduto.this.runnableOnCancel.run();
					}
				}
			}
		});		
		
	}
	
	protected ProdutoOrc loadRow(int idx) {
		List<ProdutoOrc> list = new ArrayList<>();
		/*--------------------------------------------------------------*/
		//inserir linha selecionada mais a quantidade na tabela or�amento
		ProdutoOrc pto = new ProdutoOrc();
		BigDecimal colunaQtd = new BigDecimal(JOptionPane.showInputDialog("Digite a quantidade:"));
		String colunaId = String.valueOf(table.getValueAt(idx, 0));
		String colunaDescricao = String.valueOf(table.getValueAt(idx, 1));
		String colunaVlr = String.valueOf(table.getValueAt(idx, 2));
		pto.setId(Integer.parseInt((String) colunaId));
		pto.setDescricao(colunaDescricao);
		pto.setQuantidade(colunaQtd);
		pto.setValorDolar(new BigDecimal(colunaVlr));
		list.add(pto);
		orcModel = new OrcamentoModel(list);
		return pto;
	}

	protected void busca(String trim) {
		ClasseDao dao = new ClasseDao();
		if(trim.isEmpty()){
			List<Produto> list = dao.getTodosP();
			this.prodModel = new ProdutoModel(list);
			table.setModel(prodModel);
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

	public void setOnOkB(Consumer<ProdutoOrc> consumer) {
		this.consumerOnOkB = consumer;
		
	}

}
