package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class PersonaJDBC {

	private Connection obtenerConexion() {
		Conn conn = new Conn("jdbc:mysql://localhost:3308/poo2");
		return conn.open();
	}

	public Persona personaPorId(int id) {
		String sql = "SELECT nombre " + "FROM personas " + "WHERE id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			String nombrePersona = null;
			if (result.next()) {
				nombrePersona = result.getString(1);
			}
			Proxy proxy = new Proxy(this, id);
			return new Persona(id, nombrePersona, proxy);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Set<Telefono> telefonosDePersona(int id) {

		String sql = "SELECT p.nombre,t.numero " + "FROM personas p, telefonos t "
				+ "WHERE p.id = t.id_personas AND p.id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>();
			while (result.next()) {
				telefonos.add(new Telefono(result.getString(2)));
			}
			return telefonos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}