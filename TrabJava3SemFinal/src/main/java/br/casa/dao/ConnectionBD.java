package br.casa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	
	private static ConnectionBD self;
	private Connection con;
	
	private ConnectionBD(){
		try {
			this.con = DriverManager
					.getConnection(
							"jdbc:postgresql://localhost:5432/bancodojava", 
							"postgres", "banco");
			
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						ConnectionBD.this.con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public final static synchronized ConnectionBD getInstance(){
		if(self == null){
			self = new ConnectionBD();
		}
		return self;
	}
	
	public Connection getConnection(){
		return this.con;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new CloneNotSupportedException("Só pode haver um!");
	}
	
}
