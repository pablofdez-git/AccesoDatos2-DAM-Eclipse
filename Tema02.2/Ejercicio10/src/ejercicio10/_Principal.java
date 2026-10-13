package ejercicio10;

import java.io.*;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		String archivo1 = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\archivo1.txt"; 
		String archivo2 = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\archivo2.txt";
		
		try {
			Scanner sc1 = new Scanner(new File(archivo1));
			Scanner sc2 = new Scanner(new File(archivo2));
			
			int linea = 1;					// variable que indica la línea que estamos comparando	
			boolean distintos = false;		// variable que indica si hemos encontrado alguna diferencia
			
			//funciona mientras tengan lineas y sean iguales
			while(sc1.hasNextLine() && sc2.hasNextLine() && !distintos) {
				 String texto1 = sc1.nextLine();
				 String texto2 = sc2.nextLine();
				 
				 int i = 0;	// Posición del carácter que vamos a comparar
				 while (i<texto1.length() && i<texto2.length() && texto1.charAt(i) == texto2.charAt(i)) { // Comparamos los caracteres de ambas líneas Mientras sean iguales, avanzamos a la siguiente posición
					 i++; 
				 }
				 
				// si i es menor que la longitud de ambas líneas, significa que hemos encontrado dos caracteres diferentes
				if (i<texto1.length() && i<texto2.length()) {
					System.out.println("Los archivos son distintos.");
					System.out.println("Primera diferencia en la línea: " + linea); 
					System.out.println("Carácter: " + (i + 1));
					distintos = true;
					 
				// si no hay caracteres diferentes en las posiciones comunes, comprobamos si las líneas tienen distinta longitud
				}else if (texto1.length() != texto2.length()) {
					System.out.println("Los archivos son distintos."); 
					System.out.println("Primera diferencia en la línea: " + linea); 
					System.out.println("Carácter: " + (i + 1)); 
					 
					distintos = true;
				}
				linea++;	//se suma una linea para imprimirla
			}
			
			//si no hay ningua diferencia esque son iguales
			if(!distintos) {
				System.out.println("Los archivos son iguales");
			}
			
			sc1.close();
			sc2.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
