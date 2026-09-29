package propuesta2_1;

import java.io.*;

public class _Principal {

	public static void main(String[] args) {
		listarArchivosConTamanio("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\Optativa\\Python\\ejerciciosFundamentales");
	}
	
	
	public static void listarArchivosConTamanio(String ruta) {
		File directorio = new File(ruta);
		
		if(directorio.exists()) {
			File[] listaArchivos = directorio.listFiles();
			if(listaArchivos != null) {
				for(File archivo : listaArchivos) {
					if(archivo.isFile()) {
						System.out.println(archivo.getName() + " -> "+archivo.length()+" bytes");
					}
				}
			}else {
				System.out.println("La ruta no existe o no es una carpeta");
			}
		}
	}
}
