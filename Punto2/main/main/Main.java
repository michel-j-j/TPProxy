package main;

import model.Persona;
import model.PersonaJDBC;
import model.Telefono;

public class Main {
	public static void main(String args[]) {
		PersonaJDBC dao = new PersonaJDBC();
		Persona p = dao.personaPorId(1);

		System.out.println(p.nombre());
		for (Telefono telefono : p.telefonos()) {
			System.out.println(telefono.toString());
		}
	}
}