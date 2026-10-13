package ejercicio2Ficheros;

import java.io.File;

public class _Principal {

	public static void main(String[] args) {
		crearEstructuraProyecto("MiProyectoDAM");
	}
	
	public static void crearEstructuraProyecto(String nombreProyecto) {
		String [] subdirectorios = {"src", "test", "docs", "recursos/imagenes", "recursos/datos"};
		
		for(String sub : subdirectorios) {
			File carpeta = new File(nombreProyecto + File.separator + sub);
			
			if (carpeta.exists()) {
				System.out.println("La carpeta ya existe: " + carpeta.getPath());
			} else {
				if (carpeta.mkdirs()) {
					System.out.println("Carpeta creada correctamente: " + carpeta.getPath());
				} else {
					System.out.println("Error al crear: " + carpeta.getPath());
				}
			}
		}
		
		
	}

}
