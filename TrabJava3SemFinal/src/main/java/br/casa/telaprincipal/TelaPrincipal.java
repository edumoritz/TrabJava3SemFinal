package br.casa.telaprincipal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.casa.telacadastro.AtivadorCliente;

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
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JButton btnListaurl = new JButton("ListaURL");
		GridBagConstraints gbc_btnListaurl = new GridBagConstraints();
		gbc_btnListaurl.insets = new Insets(0, 0, 0, 5);
		gbc_btnListaurl.gridx = 0;
		gbc_btnListaurl.gridy = 0;
		panel.add(btnListaurl, gbc_btnListaurl);

		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abaCliente();
			}
		});
		
		GridBagConstraints gbc_btnCliente = new GridBagConstraints();
		gbc_btnCliente.insets = new Insets(0, 0, 0, 5);
		gbc_btnCliente.gridx = 1;
		gbc_btnCliente.gridy = 0;
		panel.add(btnCliente, gbc_btnCliente);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
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
		wrapper.setAcaoExportar(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				ReportManager rm = new ReportManager();
//				rm.exportar();
			}
		});
		tabbedPane.add("Cliente", wrapper);

	}

}
