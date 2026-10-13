package ejercicio09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		String codec = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\codec.txt";
		String mensaje = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\mensaje.txt";
		String mensajeCifrado = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\mensaje_cifrado.txt";
		
		
		try {
			Scanner sc = new Scanner(new File(codec));
			String alfabeto = sc.nextLine().replaceAll("\\s+","");
			String cifrado = sc.nextLine().replaceAll("\\s+","");
			
			sc.close();
			
			Scanner scMensaje = new Scanner(new File(mensaje));
			PrintWriter salida = new PrintWriter(mensajeCifrado);
			
			while(scMensaje.hasNextLine()) {
				String linea =scMensaje.nextLine();
				String lineaCifrada = "";
				
				for(int i = 0; i<linea.length();i++) {
					char letra = linea.charAt(i);
					int posicion = alfabeto.indexOf(letra);
					
					if (posicion != -1) { 
						lineaCifrada += cifrado.charAt(posicion); 
					} else { 
						lineaCifrada += letra; 
					 }
				}
				salida.println(lineaCifrada); 
			}
			
			sc.close();
			scMensaje.close();
			
			System.out.println("Mensaje cifrado correctamente.");
			
		}catch(FileNotFoundException e) {
			System.err.println("No se encuentra el archivo" +e.getLocalizedMessage());
		}catch(IOException ex) {
			System.err.println("Error");
		}
		
		

	}

}
