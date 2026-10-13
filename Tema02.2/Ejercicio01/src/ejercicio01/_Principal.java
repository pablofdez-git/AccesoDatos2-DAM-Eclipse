package ejercicio01;

import java.io.*;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el nombre de un fichero: ");
		String fichero = sc.nextLine();
		
		if(fichero.isEmpty()) {
			System.out.println("Archivo vacio");
			fichero = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\default.txt";
		}
		
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new FileReader(fichero));
			String linea = in.readLine();
			
			while (linea != null){
				System.out.println(linea);
				linea = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			sc.close();
		}
		

	}

}
