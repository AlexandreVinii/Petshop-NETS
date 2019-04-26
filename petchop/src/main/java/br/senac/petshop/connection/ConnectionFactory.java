package br.senac.petshop.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

	// Coloquei o servertimezone, porque estava dando um erro no meu pc para conectar no local host
	private static final String URL = "jdbc:mysql://localhost/petchoperp?serverTimezone=UTC";

	private static final String USER = "root";
	private static final String PASS = "alexandre22";

	public static Connection getConnection() {
		try {
			LoadDriver.loadThis();
			return DriverManager.getConnection(URL, USER, PASS);

		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ((SQLException) ex).getSQLState());
		    System.out.println("VendorError: " + ((SQLException) ex).getErrorCode());
			throw new RuntimeException("Erro De Conexão!");
		}
	}

	public static void closeConnection(Connection cn) {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void closeConnection(Connection cn, PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		closeConnection(cn);
	}

	public static void closeConnection(Connection cn, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		closeConnection(cn, stmt);
	}

}
