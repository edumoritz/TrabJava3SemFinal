package br.casa.ativador;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

import br.casa.dao.UtilSql;
import br.casa.pojo.ProdutoOrc;
import br.casa.tabelas.OrcamentoModel;

public class AtivadorOrc {
	
	private ProdutoOrc prodSelec;
	private OrcamentoModel tableModel;
	private UtilSql dao;
	
	public AtivadorOrc(){
		configureTable();
	}

	private void configureTable() {
		ProdutoOrc pto = new ProdutoOrc();
		BigDecimal qtd = new BigDecimal(JOptionPane.showInputDialog("Digite a quantidade:"));
		pto.setQuantidade(qtd);
		
	}

}
