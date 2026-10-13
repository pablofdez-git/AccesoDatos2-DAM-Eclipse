package ejercicio07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String fichero = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\pruebaEjercicio2_7.txt";
		int contador = 0;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fichero));
			String linea = in.readLine();
			while (linea != null){
				System.out.println(linea);
				linea = in.readLine();
				contador++;
				// Pide saltar de linea si son ya se han imprimido 24 y mientras la linea siguiente no sea null
				if(contador == 24 && linea != null) {
					System.out.println("==== Pulsa enter para continuar: ====");
					sc.nextLine();
					contador=0;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error, no se ha encontrado el fichero");
		} catch(IOException e) {
			System.out.println("Error al leer el fichero");
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
