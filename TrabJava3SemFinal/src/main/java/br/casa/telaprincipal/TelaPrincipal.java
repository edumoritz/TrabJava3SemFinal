package br.casa.telaprincipal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.casa.ativador.AtivadorCliente;
import br.casa.jasper.Report;
import br.casa.telas.JanelaLista;
import br.casa.telas.PainelOrcamento;
import br.casa.telas.PainelWrepper;
import br.casa.telas.PainelWrepperOrc;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 73, 65, 85, 0 };
		gbl_panel.rowHeights = new int[] { 30, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abaCliente();
			}
		});

		JButton btnListaurl = new JButton("Produtos");
		btnListaurl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abaLista();
			}
		});
		GridBagConstraints gbc_btnListaurl = new GridBagConstraints();
		gbc_btnListaurl.insets = new Insets(0, 0, 0, 5);
		gbc_btnListaurl.gridx = 0;
		gbc_btnListaurl.gridy = 0;
		panel.add(btnListaurl, gbc_btnListaurl);
		GridBagConstraints gbc_btnCliente = new GridBagConstraints();
		gbc_btnCliente.insets = new Insets(0, 0, 0, 5);
		gbc_btnCliente.gridx = 1;
		gbc_btnCliente.gridy = 0;
		panel.add(btnCliente, gbc_btnCliente);

		JButton btnOramento = new JButton("Or\u00E7amento");
		btnOramento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abaOrcamento();
			}
		});
		GridBagConstraints gbc_btnOramento = new GridBagConstraints();
		gbc_btnOramento.gridx = 2;
		gbc_btnOramento.gridy = 0;
		panel.add(btnOramento, gbc_btnOramento);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
	}

	protected void abaOrcamento() {
		JPanel painelOrca = new PainelOrcamento();

		PainelWrepperOrc wrapper = new PainelWrepperOrc();

		wrapper.setConteudo(painelOrca);
		wrapper.setTitulo("Painel de Or\u00E7amento");

		wrapper.setAcaoFechar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});
		wrapper.setAcaoExportar(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Report rp = new Report();
				rp.exportReport();
			}
		});

		tabbedPane.add("Or\u00E7amento", wrapper);

	}

	protected void abaLista() {
		JPanel painelLista = new JanelaLista();

		PainelWrepper wrapper = new PainelWrepper();

		wrapper.setConteudo(painelLista);
		wrapper.setTitulo("Lista de Produtos");

		wrapper.setAcaoFechar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});

		tabbedPane.add("Lista", wrapper);
	}

	protected void abaCliente() {
		JPanel painelCliente = new AtivadorCliente();

		PainelWrepper wrapper = new PainelWrepper();

		wrapper.setConteudo(painelCliente);
		wrapper.setTitulo("Cadastro de Clientes");

		wrapper.setAcaoFechar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(wrapper);
			}
		});
		tabbedPane.add("Cliente", wrapper);

	}

}
