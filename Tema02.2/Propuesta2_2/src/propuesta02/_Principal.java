package propuesta02;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\entradaUsuario.txt";
		FileWriter out = null;
		Scanner sc = new Scanner(System.in);
		
		try {
			out = new FileWriter(ruta);
			System.out.println("Escribe un texto linea a linea fin para terminar");
			String linea = sc.nextLine();
			
			/*
			 * while(!linea.equals("fin)){
			 * 	for(int i=0; i<linea.length();i++){
			 * 		out.write(linea.chartAt(i));
			 * 	}
			 * out.write("\n");
			 */
			
			while(!linea.equals("fin")) {
				out.write(linea + "\n");
				linea = sc.nextLine();
			}
			System.out.println("Texto guardado");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
			}
			sc.close();
		}
		

	}

}
