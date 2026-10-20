package propuesta2_10;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class _Principal {

	public static void main(String[] args) {
		
		String estrofa = "Con diez cañones por banda,\r\n"
				+ "viento en popa a toda vela,\r\n"
				+ "no corta el mar, sino vuela\r\n"
				+ "un velero bergantín.";
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\cancionPirata.dat"));
			out.writeObject(estrofa);
			System.out.println("Archivo creado");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

	}

}
