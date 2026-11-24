package propuesta_1;

import java.sql.*;

public class _Principal {

	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost:3306/instituto? useUnicode=true&characterEncoding=UTF-8";
		final String USER = "pablo";
		final String PWD = "1234";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PWD)) {
			System.out.println("Conexión correcta con la BD Instituto.");
		} catch (SQLException e) {
			System.err.println("Error al conectar con la BD: " + e.getMessage());
			System.err.println("Código de error SQL: " + e.getErrorCode());
		}
	}

}
