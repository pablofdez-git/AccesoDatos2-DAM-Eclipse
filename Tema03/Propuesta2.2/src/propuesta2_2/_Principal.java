package propuesta2_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class _Principal {

	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost:3306/instituto? useUnicode=true&characterEncoding=UTF-8";
		final String USER = "pablo";
		final String PWD = "1234";
		int filasInsertadas = 0;
		
		try(Connection con = DriverManager.getConnection(URL, USER, PWD); 
			Statement st = con.createStatement()){
			filasInsertadas += st.executeUpdate("UPDATE ALUMNOS SET MEDIA = MEDIA + 0.5 WHERE ID_CURSO = 1");
			System.out.println("Filas actualizadas: "+filasInsertadas);
			
		} catch (SQLException e) {
			System.err.println("Error al insertar registros: "+ e.getMessage());
		}

	}

}
