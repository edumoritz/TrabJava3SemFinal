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

public class ClasseDao {
	//SQL DE CLIENTE
	private static final String SQL_C_BUSCA_TODOS = "SELECT * FROM cliente ORDER BY id";
	private static final String SQL_C_INSERE = "INSERT INTO cliente(id, nome, telefone)VALUES(?, ?, ?)";
	private static final String SQL_C_EXCLUI = "DELETE FROM cliente WHERE id = ?";
	private static final String SQL_C_ATUALIZA_NOME = "UPDATE cliente SET nome = ? WHERE id = ?";
	private static final String SQL_C_ATUALIZA_TEL = "UPDATE cliente SET telefone = ? WHERE id = ?";
	private static final String SQL_C_FILTER = "SELECT * FROM cliente WHERE nome LIKE ?";
	//SQL DE PRODUTO
	private static final String SQL_P_BUSCA_TODOS = "SELECT * FROM produto ORDER BY id";
	private static final String SQL_P_FILTER = "SELECT * FROM produto WHERE descricao LIKE ?";
	//CONECTA AO BANCO
	private Connection con = ConnectionBD.getInstance().getConnection();

	public String createTableSql() {
		StringBuilder sb = new StringBuilder();
		Cliente pt = new Cliente();
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
					tipoColuna = "INT NOT NULL";
				} else if (tipoParametro.equals(BigDecimal.class)) {
					tipoColuna = "NUMERIC NOT NULL";
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

	public List<Cliente> getTodosC() {
		List<Cliente> lista = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(SQL_C_BUSCA_TODOS); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Cliente ct = new Cliente();
				ct.setId(rs.getInt(1));
				ct.setNome(rs.getString(2));
				ct.setTelefone(rs.getString(3));
				lista.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Produto> getTodosP() {
		List<Produto> lista = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(SQL_P_BUSCA_TODOS); ResultSet rs = ps.executeQuery()) {
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
	public List<Cliente> filterCliente(String palavra) {
		List<Cliente> lista = new ArrayList<>();

		try {
			PreparedStatement ps = con.prepareStatement(SQL_C_FILTER);
			ps.setString(1, "%"+palavra+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente ct = new Cliente();
				ct.setId(Integer.parseInt(rs.getString(1)));
				ct.setNome(rs.getString(2));
				ct.setTelefone(rs.getString(3));
				lista.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public List<Produto> filterProduto(String palavra) {
		List<Produto> lista = new ArrayList<>();

		try {
			PreparedStatement ps = con.prepareStatement(SQL_P_FILTER);
			ps.setString(1, "%"+palavra+"%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produto pt = new Produto();
				pt.setId(Integer.parseInt(rs.getString(1)));
				pt.setDescricao(rs.getString(2));
				pt.setValorDolar(new BigDecimal(rs.getString(3)));
				lista.add(pt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public void insereC(Cliente c) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_C_INSERE);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getTelefone());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizaC(int id, Cliente c) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL_C_ATUALIZA_NOME);
			ps.setString(1, c.getNome());
			ps.setInt(2, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			ps = con.prepareStatement(SQL_C_ATUALIZA_TEL);
			ps.setString(1, c.getTelefone());
			ps.setInt(2, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluiC(int id) {
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(SQL_C_EXCLUI);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
