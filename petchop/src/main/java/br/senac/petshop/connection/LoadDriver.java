package br.senac.petshop.connection;

public class LoadDriver {

	public static void loadThis() {

		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception ex) {
			// handle the error
		}

	}

}
