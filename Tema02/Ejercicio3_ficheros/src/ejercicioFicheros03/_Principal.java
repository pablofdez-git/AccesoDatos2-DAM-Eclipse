    package ejercicioFicheros03;

import java.io.File;

public class _Principal {

	public static void main(String[] args) {

		File ruta  = new File("C:\\Users\\pablo\\OneDrive\\2ºDAM\\Optativa\\Python\\Mix");
		renombrar(ruta, "opt_");

	}
	
	public static void renombrar(File carpeta, String prefijo) {
		if(!carpeta.exists() || !carpeta.isDirectory()) {
			System.out.println("Error: La ruta no existe o no es una carpeta");
			return;
		}
		
		File[] elementos = carpeta.listFiles();
		
		if(elementos == null) {
			System.out.println("No se pudo leer el contenido de la carpeta");
			return;
		}
		
		for(File elemento : elementos) {
			if(elemento.isFile()) {
				String nuevoNombre = prefijo + elemento.getName();
				File nuevoDestino = new File(carpeta, nuevoNombre);
				boolean existo = elemento.renameTo(nuevoDestino);
				if(existo) {
					System.out.println("Renombrado: " + elemento.getName() + " -> " + nuevoNombre);
				}else {
					System.out.println("Fallo al renombrar: " + elemento.getName());
				}
			}
		}
	}

}
