package ejercicio8_ficheros;

import java.io.File;

public class _Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// int contarFicherosVacios(File carpeta)
	// int eliminarFicherosVacios(File carpeta)
	// usar un arrayList
	
	public static void recorrer(String ruta) {
		File rut = new File(ruta);
		int contadorVacios = 0;
		
		if(rut.exists() && rut.isDirectory()) {
			File[] listarArchivos = rut.listFiles();
			for(File archivo : listarArchivos) {
				if(archivo.isFile()) {
					if(archivo.length() == 0) {
						contadorVacios++;
					}
				}else if(archivo.isDirectory()) {
					recorrer(archivo.getPath());
				}
			}
		}
	}
	
	

}
