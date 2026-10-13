package ejercicio4_ficheros;

import java.io.File;

public class _Principal {

	public static void main(String[] args) {
		File ruta = new File("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\Optativa\\Python\\Mix");
		
		long totalBytes = calcularTamanioTotal(ruta);
		
		double totalKB = totalBytes / 1024.0;
		double totalMB = totalKB / 1024.0;
		
		System.out.println("Tamaño total de la carpeta '" + ruta.getName() + "':");
		System.out.println(totalBytes + " Bytes");
		System.out.printf("%.2f KB\n", totalKB);
		System.out.printf("%.2f MB\n", totalMB);
	}
	
	public static long calcularTamanioTotal(File carpeta) {
		long result = 0;
		if(!carpeta.exists()) {
			result = 0;
		}
		
		if(carpeta.isFile()) {
			result = carpeta.length();
		}
		File[] elementos = carpeta.listFiles();
		
		if(elementos != null) {
			for(File elemento : elementos) {
				if(elemento.isFile()){
					result += elemento.length();
				}else if (elemento.isDirectory()) {
					result += calcularTamanioTotal(elemento);
				}
			}
		}
		
		return result;
	}

}
