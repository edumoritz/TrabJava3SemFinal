package planodecontas;

import java.math.BigDecimal;

import javax.swing.JOptionPane;

import planodecontas.UniNodeImpl;
import planodecontas.UniNode;
import planodecontas.UtilConta;
import planodecontas.UtilTree;

public class Main {
	
	public Main() {
		Conta contaAgua = new Conta(1, "Água", new BigDecimal("101.28"));
		UniNode<Conta> noAgua = new UniNodeImpl<>(contaAgua);
		Conta contaAluguel = new Conta(2, "Aluguel", new BigDecimal("900.00"));
		UniNode<Conta> noAlu = new UniNodeImpl<>(contaAluguel);
		Conta contaIntTel = new Conta(3, "Internet e Telefone", new BigDecimal(
				"165.35"));
		UniNode<Conta> noIntTel = new UniNodeImpl<>(contaIntTel);
		Conta contaEnElet = new Conta(4, "Energia Elétrica", new BigDecimal(
				"252.58"));
		UniNode<Conta> noEnElet = new UniNodeImpl<>(contaEnElet);
		// PAI 1
		Conta despesasAdm = new Conta(1, "Despesas Administrativas",
				new BigDecimal(0));
		UniNode<Conta> noDeAdm = new UniNodeImpl<>(despesasAdm);
		noDeAdm.addFilho(noAgua);
		noDeAdm.addFilho(noAlu);
		noDeAdm.addFilho(noIntTel);
		noDeAdm.addFilho(noEnElet);
		
		// FILHOS PAI 2
		Conta beneficios = new Conta(1, "Benefícios", new BigDecimal("100.00"));
		UniNode<Conta> noBen = new UniNodeImpl<>(beneficios);
		Conta encargos = new Conta(2, "Encargos", new BigDecimal("80.00"));
		UniNode<Conta> noEnc = new UniNodeImpl<>(encargos);
		Conta salario = new Conta(3, "Salário", new BigDecimal("2000.00"));
		UniNode<Conta> noSal = new UniNodeImpl<>(salario);
		// PAI 2
		Conta gastosPessoal = new Conta(2, "Gastos com Pessoal",
				new BigDecimal(0));
		UniNode<Conta> noGasPes = new UniNodeImpl<>(gastosPessoal);
		noGasPes.addFilho(noBen);
		noGasPes.addFilho(noEnc);
		noGasPes.addFilho(noSal);
		
		// FILHOS PAI 3
		Conta servicoLimpeza = new Conta(1, "Limpeza", new BigDecimal("150.00"));
		UniNode<Conta> noLimp = new UniNodeImpl<>(servicoLimpeza);
		Conta servicoManutencao = new Conta(2, "Manutenção", new BigDecimal(
				"500.00"));
		UniNode<Conta> noManu = new UniNodeImpl<>(servicoManutencao);
		// PAI 3
		Conta manutencaoLimpeza = new Conta(3, "Manutenção e Limpeza",
				new BigDecimal(0));
		UniNode<Conta> noManuLimp = new UniNodeImpl<>(manutencaoLimpeza);
		noManuLimp.addFilho(noLimp);
		noManuLimp.addFilho(noManu);
		
		// FILHOS PAI 4
		Conta matEscritorio = new Conta(1, "Materiais de Escritório",
				new BigDecimal("600.00"));
		UniNode<Conta> noMatEsc = new UniNodeImpl<>(matEscritorio);
		Conta matLimpeza = new Conta(2, "Materiais de Limpeza", new BigDecimal(
				"300.00"));
		UniNode<Conta> noMatLimp = new UniNodeImpl<>(matLimpeza);
		// PAI 4
		Conta materiais = new Conta(4, "Materiais", new BigDecimal(0));
		UniNode<Conta> noMat = new UniNodeImpl<>(materiais);
		noMat.addFilho(noMatEsc);
		noMat.addFilho(noMatLimp);
		
		// FILHOS PAI 5
		Conta filho1 = new Conta(1, "Filho 1", new BigDecimal("200.00"));
		UniNode<Conta> noFilho1 = new UniNodeImpl<>(filho1);
		Conta filho2 = new Conta(2, "Filho 2", new BigDecimal("300.00"));
		UniNode<Conta> noFilho2 = new UniNodeImpl<>(filho2);
		// PAI 5
		Conta teste = new Conta(5, "Teste Pai 5", new BigDecimal(0));
		UniNode<Conta> noTeste = new UniNodeImpl<>(teste);
		noTeste.addFilho(noFilho1);
		noTeste.addFilho(noFilho2);
		
		// PAI DE TODOS
		Conta despesasOper = new Conta(1, "Despesas Operacionais",
				new BigDecimal(0));
		UniNode<Conta> noDeOpr = new UniNodeImpl<>(despesasOper);
		noDeOpr.addFilho(noDeAdm);
		noDeOpr.addFilho(noGasPes);
		noDeOpr.addFilho(noManuLimp);
		noDeOpr.addFilho(noMat);
		noDeOpr.addFilho(noTeste);
		
		UtilConta<Conta> planoContas = new UtilTree<Conta>(noDeOpr);
		
		planoContas.AllConsole();;

	}
	
	
	public static void main(String[] args) {
		new Main();
	}

}
