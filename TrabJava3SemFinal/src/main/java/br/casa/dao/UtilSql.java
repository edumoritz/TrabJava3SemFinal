package br.casa.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.casa.planodecontas.ConnectionBD;
import br.casa.pojo.Cliente;
import br.casa.pojo.Produto;

public class UtilSql {

	private Connection con = ConnectionBD.getInstance().getConnection();
	private static final String SQL_L_BUSCA_TODOS = "SELECT * FROM produto";
	private static final String SQL_ORC_BUSCA_TODOS = "SELECT * FROM orcamento";
	private static final String SQL_ORC_DROP = "DELETE * FROM orcamento WHERE id = ?";

	public String createTableSql() {
		StringBuilder sb = new StringBuilder();
		Produto pt = new Produto();
		try {
			Class<?> clazz = pt.getClass();

			sb.append("CREATE TABLE ").append(clazz.getSimpleName()).append(" (");

			Field[] fields = clazz.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field fd = fields[i];

				String nomeColuna = fd.getName();
				String tipoColuna;

				Class<?> tipoParametro = fd.getType();

				if (tipoParametro.equals(String.class)) {
					tipoColuna = "VARCHAR(100) NOT NULL";
				} else if (tipoParametro.equals(int.class)) {
					tipoColuna = "INT  NOT NULL";
				} else if (tipoParametro.equals(BigDecimal.class)) {
					tipoColuna = "NUMERIC  NOT NULL";
				} else {
					tipoColuna = "DESCONHECIDO";
				}
				if (i > 0) {
					sb.append(",");
				}
				sb.append("\n\t").append(nomeColuna).append(' ').append(tipoColuna);
			}
			sb.append(",\n\tPRIMARY KEY(");
			for (int i = 0; i < fields.length; i++) {
				Field fd = fields[i];
				Class<?> tipoParametro = fd.getType();
				if (tipoParametro.equals(int.class)) {
					sb.append(fd.getName());
				}
			}
			sb.append(")").append("\n);");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());

		try {
			con.prepareStatement(sb.toString()).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Junto a tabela Produto cria a do orcamento
		createTableOrcamento();

		return sb.toString();
	}

	private String createTableOrcamento() {
		StringBuilder sb = new StringBuilder();
		Produto pt = new Produto();
		try {
			Class<?> clazz = pt.getClass();

			sb.append("CREATE TABLE ").append("orcamento").append(" (");

			Field[] fields = clazz.getDeclaredFields();

			for (int i = 0; i < fields.length; i++) {
				Field fd = fields[i];

				String nomeColuna = fd.getName();
				String tipoColuna;

				Class<?> tipoParametro = fd.getType();

				if (tipoParametro.equals(String.class)) {
					tipoColuna = "VARCHAR(100) NOT NULL";
				} else if (tipoParametro.equals(int.class)) {
					tipoColuna = "INT  NOT NULL";
				} else if (tipoParametro.equals(BigDecimal.class)) {
					tipoColuna = "NUMERIC  NOT NULL";
				} else {
					tipoColuna = "DESCONHECIDO";
				}
				if (i > 0) {
					sb.append(",");
				}
				sb.append("\n\t").append(nomeColuna).append(' ').append(tipoColuna);
			}
			sb.append(",\n\tPRIMARY KEY(");
			for (int i = 0; i < fields.length; i++) {
				Field fd = fields[i];
				Class<?> tipoParametro = fd.getType();
				if (tipoParametro.equals(int.class)) {
					sb.append(fd.getName());
				}
			}
			sb.append(")").append("\n);");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());

		try {
			con.prepareStatement(sb.toString()).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public String insertSql(Produto produto) {
		StringBuilder sb = new StringBuilder();
		Produto pt = new Produto();

		try {
			Class<?> clazz = pt.getClass();
			Field[] fields = clazz.getDeclaredFields();

			sb.append("INSERT INTO ").append(clazz.getSimpleName()).append(" (");

			for (int i = 0; i < fields.length; i++) {
				Field fd = fields[i];
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(fd.getName());
			}

			sb.append(")").append(" VALUES (");

			sb.append(produto.getId()).append(", ").append("'" + produto.getDescricao() + "'").append(", ");
			sb.append(produto.getValorDolar());
			sb.append(");");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());

		try {
			con.prepareStatement(sb.toString()).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public String insertSqlProd(Produto produto) {
		StringBuilder sb = new StringBuilder();
		Produto pt = new Produto();

		try {
			Class<?> clazz = pt.getClass();
			Field[] fields = clazz.getDeclaredFields();

			sb.append("INSERT INTO ").append("orcamento").append(" (");

			for (int i = 0; i < fields.length; i++) {
				Field fd = fields[i];
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(fd.getName());
			}

			sb.append(")").append(" VALUES (");

			sb.append(produto.getId()).append(", ").append("'" + produto.getDescricao() + "'").append(", ");
			sb.append(produto.getValorDolar());
			sb.append(");");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());

		try {
			con.prepareStatement(sb.toString()).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public List<Produto> getTodos() {
		List<Produto> lista = new ArrayList<>();
		try (PreparedStatement ps = con.prepareStatement(SQL_L_BUSCA_TODOS); ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(rs.getString(1)));
				p.setDescricao(rs.getString(2));
				p.setValorDolar(new BigDecimal(rs.getString(3)));
				lista.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public List<Produto> getTodosP() {
		List<Produto> lista = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(SQL_ORC_BUSCA_TODOS); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Produto ct = new Produto();
				ct.setId(rs.getInt(1));
				ct.setDescricao(rs.getString(2));
				ct.setValorDolar(new BigDecimal(rs.getString(3)));
				lista.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public void dropRow(Object idx){
		try {
			String str = "DELETE * FROM orcamento WHERE id = "+idx;
			
//			try {
//				con.prepareStatement(str).execute();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean VerificarNoBanco() {
		boolean existe = true;
		String str = "SELECT * FROM produto";

		try {
			PreparedStatement ps = con.prepareStatement(str);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				existe = false;
			} else {
				existe = true;
			}
		} catch (SQLException e) {
			System.out.println("Necessita criar uma tabela");
		}
		return existe;

	}
}
