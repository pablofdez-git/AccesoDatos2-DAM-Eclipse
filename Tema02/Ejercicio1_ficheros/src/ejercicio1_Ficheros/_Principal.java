package ejercicio1_Ficheros;

import java.io.File;

public class _Principal {

	public static void main(String[] args) {
		
		ficha("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\Optativa\\Python\\EjemploClase.py");
		
		System.out.println("-----------------------------------------------");
		
		ficha("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\Optativa\\Python\\Cadenas");
		
		System.out.println("-----------------------------------------------");
		
		ficha("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\Optativa\\Python\\Cdenas");
	}
	
	public static void ficha(String ruta) {
		
		File rut = new File(ruta);
		
		if(rut.exists()) {
			
			System.out.println("--- Ficha del Elemento ---");
			System.out.println("Ruta: "+ruta);
			
			if(rut.isDirectory()) {
				System.out.println("Tipo: Directorio");
			}else if(rut.isFile()){
				System.out.println("Tipo: Fichero");
			}
			
			System.out.println("Nombre: "+rut.getName());
			System.out.println("Ruta absoluta: "+rut.getAbsolutePath());
			System.out.println("Directorio Padre: " +rut.getParent());
			
			if(rut.isFile()) {
				System.out.println("Tamaño: " +rut.length()+ " Bytes");
				String lectura = rut.canRead() ? "si" : "no";
				String escritura = rut.canWrite() ? "si" : "no";
				String ejecutar = rut.canExecute() ? "si" : "no";
				
				System.out.println("Lectura: "+lectura+" | Escritura: "+escritura+" | Ejecuta: " +ejecutar);
			}else if (rut.isDirectory()) {
				String elementos [] = rut.list();
				System.out.println("Tiene "+elementos.length+" elementos");
			}
			
		}else {
			System.err.println("La ruta no exixte");
		}
		
	}

}
