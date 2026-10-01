package propuesta2_2;

import java.io.File;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);				//C:\Users\Alumno\OneDrive\2ºDAM\Optativa\Python
		
		System.out.println("------ Menu ------");
		System.out.println("Introduce la ruta: ");
		String ruta = sc.next();
		System.out.println("1. Listado Sencillo");
		System.out.println("2. Listado Complejo");
		int respuesta = sc.nextInt();
		
		if(respuesta == 1) {
			listadoSencillo(ruta);
		}else if(respuesta == 2) {
			listadoComplejo(ruta,0);
		}else {
			System.out.println("Valor incorrecto");
		}
		
		sc.close();
		
	}
	
	public static void listadoSencillo(String ruta) {
		File rut = new File(ruta);
		
		if(rut.exists() && rut.isDirectory()) {
			File[] listaArchivos = rut.listFiles();
			for(File archivo : listaArchivos){
				if(archivo.isFile()) {
					double tamanioKB = archivo.length() / 1024.0;
					System.out.println("Fichero -> "+archivo.getName()+ " - Tamaño: "+tamanioKB+" KB");
					
				}else if(archivo.isDirectory()) {
					System.out.println("Directorio -> "+archivo.getName());
				}
			}
		}else {
			System.out.println("La ruta no existe o no es un directorio");
		}
	}
	
	public static void listadoComplejo(String ruta, int nivel) {
		File rut = new File(ruta);
		
		if(rut.exists() && rut.isDirectory()) {
			File[] listaArchivos = rut.listFiles();
			for(File archivo : listaArchivos) {
				 String tabuladores = "\t".repeat(nivel);
				if(archivo.isFile()) {
					double tamanioKB = archivo.length() / 1024.0;
					System.out.println(tabuladores+"Fichero -> "+archivo.getName()+ " - Tamaño: "+tamanioKB+" KB");
				}else if(archivo.isDirectory()) {
					System.out.println(tabuladores+"Directorio -> "+archivo.getName());
					listadoComplejo(archivo.getPath(), nivel + 1);
				}
			}
		}else {
			System.out.println("La ruta no existe o no es un directorio");
		}
	}

}
