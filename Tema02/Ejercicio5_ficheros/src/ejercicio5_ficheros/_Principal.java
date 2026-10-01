package ejercicio5_ficheros;

import java.io.*;
import java.util.*;

public class _Principal {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce la carpeta: ");
		String ruta = sc.nextLine();
		
		File rut = new File(ruta);
		
		
		
		
	}
	
	public static void tamanio(File carpeta) {
		File mayor = null;
		File menor = null;
		
		if(carpeta.exists()) {
			File[] listarArchivos = carpeta.listFiles();
			if(listarArchivos != null) {
				for(File archivo : listarArchivos) {
					if(archivo.isFile()) {
						if(mayor == null && menor == null) {
							mayor = archivo;
							menor = archivo;
						}
						if(archivo.length() > mayor.length()) {
							mayor = archivo;
						}else if(archivo.length() < menor.length()) {
							menor = archivo;
						}
					}else if(archivo.isDirectory()) {
						tamanio(archivo);
					}
				}
			}else {
				System.err.println("No hay ningun archivo");
			}
		}else {
			System.err.println("La carpeta no existe");
		}
	}

}
