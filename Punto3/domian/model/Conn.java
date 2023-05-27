package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Conn {

	private static final String PWD = "";
	private static final String USER = "root";
	private String conn;
	private Connection connection;

	public Conn(String conn) {
		this.conn = conn;
	}

	Connection open() {
		try {
			String url = this.conn;
			String user = USER;
			String password = PWD;

			connection = DriverManager.getConnection(url, user, password);
			this.connection = connection;

			return connection;
		} catch (Exception ex) {
			throw new RuntimeException("No fue posible conectarse a la base de datos " + ex.getMessage());
		}
	}

	void close() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("No fue posible cerrar la conexion a la base de datos" + e.getMessage());
		}
	}
}