package propuesta06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		int contador = 0;
		BufferedReader in = null;
		
		try {
			// Abre el archivo usando un buffer para optimizar la lectura en memoria
			in = new BufferedReader(new FileReader("entradaUsuario.txt"));
			
			// Lee la primera línea del fichero
			String linea = in.readLine();

			// Itera mientras no se alcance el final del fichero (null)
			while(linea != null) {
				
				// Pasa la línea al Scanner para dividirla en tokens (palabras)
				Scanner sc = new Scanner(linea);
				
				// Comprueba si quedan tokens en la línea actual
				while(sc.hasNext()) {
					sc.next();  // Consume el token actual para poder avanzar
					contador++; // Incrementa el recuento
				}
				
				// Lee la siguiente línea antes de la nueva iteración
				linea = in.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error E/S");
			e.getMessage();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		
		System.out.println("El archivo tiene " + contador + " palabras");
	}
}