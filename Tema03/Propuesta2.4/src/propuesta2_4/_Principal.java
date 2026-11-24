package propuesta2_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class _Principal {

	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost:3306/instituto? useUnicode=true&characterEncoding=UTF-8";
		final String USER = "pablo";
		final String PWD = "1234";
		
		String sql = "SELECT num, nombre, fnac, media FROM alumnos WHERE id_curso = 1";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PWD);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql)) {
			
			while (rs.next()){
				int id = rs.getInt("num");
				String nombre = rs.getString("nombre");
				java.sql.Date fnac = rs.getDate("fnac");
				double media = rs.getDouble("media");
				String textoNota = rs.wasNull() ? "Pendiente de calificación" : String.valueOf(media);
				
				System.out.println(id + " - " + nombre + "| Nacimiento: " + fnac + "| Media: " + textoNota);
			}
			
		} catch (SQLException e) {
			System.err.println("Error al consultar alumnos: " + e.getMessage());
		}

	}

}
