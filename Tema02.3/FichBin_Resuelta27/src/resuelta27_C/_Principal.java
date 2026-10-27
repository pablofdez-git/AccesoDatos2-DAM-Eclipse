package resuelta27_C;

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
		
		
		// BUSQUEDA POR ID (Lectura con validación de borrado)
		int idBuscado = 4;
		
		try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
			
			int totalRegistros = (int) (raf.length() / TAMANO_REGISTRO);

			for (int i = 0; i < totalRegistros; i++) {
			    raf.seek(i * (long) TAMANO_REGISTRO);	// Si los ID no son secuenciales o hay huecos por borrados, se recorre el fichero salto a salto comprobando el estado del registro.
			    
			    boolean activo = raf.readBoolean();
			    int id = raf.readInt();
			    
			    if (activo && id == idBuscado) {
			        StringBuilder sb = new StringBuilder();
			        for (int j = 0; j < 10; j++) sb.append(raf.readChar());
			        String nombre = sb.toString().trim();
			        
			        double precio = raf.readDouble();
			        
			        System.out.println("Producto encontrado: " + nombre + " (" + precio + "€)");
			        break;
			    } 
			    
			}
		 
		} catch (FileNotFoundException ex) {
		    System.out.println(ex.getMessage());
		} catch (IOException ex) {
		    System.out.println(ex.getMessage());
		}

	}

}
