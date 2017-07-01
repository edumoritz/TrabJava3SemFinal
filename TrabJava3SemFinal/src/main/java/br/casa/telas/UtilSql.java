package br.casa.telas;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.casa.planodecontas.ConnectionBD;


public class UtilSql {
	
	private Connection con = ConnectionBD.getInstance().getConnection();
	
	
	public String createTableSql(){
		StringBuilder sb = new StringBuilder();
		Produto pt = new Produto();
		try {
			Class<?> clazz = pt.getClass();
			
			sb.append("CREATE TABLE ").append(clazz.getSimpleName()).append(" (");
			
			Field[] fields = clazz.getDeclaredFields();
			
			for(int i = 0; i < fields.length; i++){
				Field fd = fields[i];
				
				String nomeColuna = fd.getName();
				String tipoColuna;
				
				Class<?> tipoParametro = fd.getType();
				
				if(tipoParametro.equals(String.class)){
					tipoColuna = "VARCHAR(100) NOT NULL";
				} else if (tipoParametro.equals(Long.class)){
					tipoColuna = "INT  NOT NULL";
				} else if (tipoParametro.equals(BigDecimal.class)) {
					tipoColuna = "NUMERIC  NOT NULL";
				} else {
					tipoColuna = "DESCONHECIDO";
				}
				if(i > 0){
					sb.append(",");
				}
				sb.append("\n\t").append(nomeColuna).append(' ').append(tipoColuna);
			}
			sb.append(",\n\tPRIMARY KEY(");
			for(int i = 0; i < fields.length; i++){
				Field fd = fields[i];
				Class<?> tipoParametro = fd.getType();
				if(tipoParametro.equals(Long.class)){
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
	
	public String insertSql(Produto produto){
		StringBuilder sb = new StringBuilder();
		Produto pt = new Produto();
		
		try {
			Class<?> clazz = pt.getClass();
			Field[] fields = clazz.getDeclaredFields();
			
			sb.append("INSERT INTO ").append(clazz.getSimpleName()).append(" (");
			
			for(int i = 0; i < fields.length; i++){
				Field fd = fields[i];
				if(i > 0){
					sb.append(", ");
				}
				sb.append(fd.getName());
			}
			
			sb.append(")").append(" VALUES (");
			
			sb.append(produto.getId()).append(", ").append("'"+produto.getDescricao()+"'").append(", ");
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
}
