package propuesta2_3;

import java.sql.*;

public class _Principal {

	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost:3306/instituto? useUnicode=true&characterEncoding=UTF-8";
		final String USER = "pablo";
		final String PWD = "1234";
		
		int filaselimindas = 0;
		
		try(Connection con = DriverManager.getConnection(URL, USER, PWD); 
			Statement st = con.createStatement()){
			filaselimindas += st.executeUpdate("DELETE FROM ALUMNOS WHERE NUM = 13");
			System.out.println("Filas eliminadas: "+filaselimindas);
		} catch (SQLException e) {
			System.err.println("Error al insertar registros: "+ e.getMessage());
		}

	}

}
