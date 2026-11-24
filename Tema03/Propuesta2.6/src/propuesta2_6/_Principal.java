package propuesta2_6;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class _Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		final String URL = "jdbc:mysql://localhost:3306/instituto? useUnicode=true&characterEncoding=UTF-8";
		final String USER = "pablo";
		final String PWD = "1234";
		
		String sqlIns = "INSERT INTO ALUMNOS (NOMBRE, FNAC, MEDIA, ID_CUROS) VALUES (?,?,?,?)";
		
		System.out.print("Introduce el nombre: ");
		String nombre = sc.nextLine();
		System.out.println("Introduce la fecha de nacimiento: ");
		String fnac = sc.nextLine();
		System.out.println("Introduce la media: ");
		String mediaTexto = sc.nextLine();
		System.out.println("Introduce el id del curso: ");
		int idCurso = sc.nextInt();
		
		try(Connection con = DriverManager.getConnection(URL, USER, PWD)){
			try(PreparedStatement psIns = con.prepareStatement(sqlIns)){
				int i = 1;
				psIns.setString(i++, nombre);
				psIns.setDate(i++, java.sql.Date.valueOf(fnac));
				if(mediaTexto.isBlank()) {
					psIns.setNull(i++, java.sql.Types.DECIMAL);
				} else {
					double media = Double.parseDouble(mediaTexto);
					psIns.setDouble(i++, media);
				}
				psIns.setInt(i++, idCurso);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		
		

	}

}
