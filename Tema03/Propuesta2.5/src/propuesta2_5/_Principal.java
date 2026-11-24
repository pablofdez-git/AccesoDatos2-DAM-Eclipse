package propuesta2_5;

import java.sql.*;
import java.util.*;

public class _Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		final String URL = "jdbc:mysql://localhost:3306/instituto? useUnicode=true&characterEncoding=UTF-8";
		final String USER = "pablo";
		final String PWD = "1234";
		
		System.out.print("Introduce una nota de corte: ");
		double notaCorte = sc.nextDouble();
		
		String sql = "SELECT num, nombre, fnac, media FROM alumnos  WHERE media > ?";
		
		try(Connection con = DriverManager.getConnection(URL, USER, PWD)){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, notaCorte);
			
			try (ResultSet rs = ps.executeQuery()) {
				int contador = 0;
				while (rs.next()) {
					System.out.println(rs.getInt("num") + " - " + rs.getString("nombre") + " | Nacimiento: " + rs.getDate("fnac")
							+ " | Media: " + rs.getDouble("media"));
					contador++;
				}
				if (contador == 0) {
					System.out.println("Ningún alumno supera la nota " + notaCorte);
				} else {
					System.out.println("Total: " + contador + " alumnos");
				}
			}
			
		} catch (SQLException e) {
			System.err.println("Error al consultar alumnos: " + e.getMessage());
		}

	}

}
