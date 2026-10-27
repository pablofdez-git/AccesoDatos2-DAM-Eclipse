package resuelta27_D;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

	/*	
		Long: 8 bytes
		Integer: 4 bytes
		Short: 2 bytes
		Byte: 1 byte
		Double: 8 bytes
		Float: 4 bytes
		Boolean: 1 byte
		Char: 2 bytes
		String: 2 bytes por cada caracter
	*/


public class _Principal {
	
	public static void main(String[] args) {
		
		final int LONGITUD_NOMBRE = 10;
		
		//Se debe conocer cuanto ocupa cada registro, para ello se tiene que tener en cuenta los tipos de datos que almacena y lo que estos ocupan en memoria
        //En el caso del registro que se presenta: (1 (boolean) + 4 (int) + (10 * 2) (string) + 8 (double) + 1 (boolean) + 2 (char)) = 35 bytes por cada registro
	    final int TAMANO_REGISTRO = 1 + 4 + (LONGITUD_NOMBRE * 2) + 8 + 1 + 2; 	// 36 bytes
		
		String ruta = "C:\\Users\\AidaRdgz\\workespaceAADD_ficherosBinarios\\FICHEROS\\listaProductos_v2.dat";
		
		
		// ACTUALZIAR PRECIO DE UN PRODUCTO (Para modificar un dato concreto sin reescribir todo el registro se calcula el offset (desplazamiento) exacto hasta el atributo a cambiar)
		int nRegistro = 2;
		double nuevoPrecio = 19.99;
		
		// Desplazamiento interno hasta el 'precio': 1 byte (activo) + 4 bytes (id) + 20 bytes (nombre) = 25 bytes de offset
		long posicionRegistro = (nRegistro - 1) * (long) TAMANO_REGISTRO;
		long offsetPrecio = posicionRegistro + 1 + 4 + 20; 		// Posición exactas del double 'precio'
		
		try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
			
			raf.seek(offsetPrecio);
			raf.writeDouble(nuevoPrecio); 	// Sobrescribe únicamente los 8 bytes del precio
			
			System.out.println("Precio del registro " + nRegistro + " actualizado a " + nuevoPrecio + "€");
		 
		} catch (FileNotFoundException ex) {
		    System.out.println(ex.getMessage());
		} catch (IOException ex) {
		    System.out.println(ex.getMessage());
		}

	}

}
