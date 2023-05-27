package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC {

	private Connection obtenerConexion() {
		Conn conn = new Conn("jdbc:mysql://localhost:3308/poo2");
		return conn.open();
	}

	public List<Map<String, String>> recuperarPersonasLisMap(String sql, Connection conn) {
		try (PreparedStatement statement = conn.prepareStatement(sql);) {
			ResultSet result = statement.executeQuery();
			Map<String, String> mapa = new HashMap<String, String>();
			List<Map<String, String>> listaPersonas = new ArrayList<Map<String, String>>();
			String nombrePersona = null;
			String id = null;

			while (result.next()) {
				nombrePersona = result.getString(1);
				id = result.getString(2);
				mapa.put(id, nombrePersona);
				listaPersonas.add(mapa);
			}
			return listaPersonas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String[]> recuperarPersonasListVec(String sql, Connection conn) {
		try (PreparedStatement statement = conn.prepareStatement(sql);) {

			String nombre;
			String id;

			ResultSet result = statement.executeQuery();
			List<String[]> lista = new ArrayList<String[]>();

			while (result.next()) {
				id = result.getString(1);
				nombre = result.getString(2);

				String[] vector = { id, nombre };
				lista.add(vector);
			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
