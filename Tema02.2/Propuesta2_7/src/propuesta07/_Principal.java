package propuesta07;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class _Principal {

	public static void main(String[] args) {
		
		BufferedWriter out = null;
		int numLineas = 10;						// numero de lineas
		String nombreArchivo = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\lineas.txt";	//ruta donde crear el archivo y su nombre
		
		try {
			out = new BufferedWriter(new FileWriter(nombreArchivo));
			
			for(int i = 1; i <= numLineas; i++) {
				out.write("Esta es la lina "+i);
				out.newLine();
			}
			System.out.println("Archivo creado");
		} catch (IOException e) {
			System.out.println("error");
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

