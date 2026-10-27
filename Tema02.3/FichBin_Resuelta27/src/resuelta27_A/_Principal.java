package resuelta27_A;

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
        //En el caso del registro que se presenta: (4 (int) + (10 * 2) (string) + 8 (double) + 1 (boolean) + 2 (char)) = 35 bytes por cada registro
	    final int TAMANO_REGISTRO = 4 + (LONGITUD_NOMBRE * 2) + 8 + 1 + 2; 	// 35 bytes
		
		String ruta = "C:\\Users\\AidaRdgz\\workespaceAADD_ficherosBinarios\\FICHEROS\\listaProductos.dat";
		
		ArrayList<Producto> productos = new ArrayList<>();
		 
		productos.add(new Producto(1, "Producto 1", 10.5, true, 'T'));
		productos.add(new Producto(2, "Producto 2", 15.1, true, 'R'));
		productos.add(new Producto(3, "Producto 3", 11.5, false, 'T'));
		productos.add(new Producto(4, "Producto 4", 50, false, 'D'));
		productos.add(new Producto(5, "Producto 5", 79.3, true, 'U'));
		

		try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
			
			// ESCRITURA
			for (Producto p: productos) {
				 
		        raf.writeInt(p.getId());
		 
		        //El string tiene que tener un tamaño fijo para poder controlar hasta donde llega el registro
		        //Si el tamaño fuera variable el tamaño de cada registro variaria y no se podria acceder a cada registro teniendo en cuenta lo que ocupa		
		        StringBuffer sb = new StringBuffer(p.getNombre());
		        sb.setLength(LONGITUD_NOMBRE);		//Con setLength lo que hace es tomar lo que haya en el buffer, si es mayor que longitud 10 
		        									//lo corta y si es menor lo completa con caracteres nulos Unicode '\u0000'
		        raf.writeChars(sb.toString());		//Se escribe caracter a caracter el contenido del buffer 	
		        
		        raf.writeDouble(p.getPrecio());
		        raf.writeBoolean(p.isDescuento());
		        raf.writeChar(p.getTipo());

			}
			
			
			// LECTURA
	        //Acceder al segundo registro (Registro 2 -> Índice 1)
			int nRegistro = 2;
            long posicion = (nRegistro - 1) * (long) TAMANO_REGISTRO;		// 35 (se empieza a contar en 0)
	        raf.seek(posicion); 		
	        
	        //Ahora se debe leer tal y como se ha escrito. 
	        int id = raf.readInt();
            
            StringBuilder nombreBuilder = new StringBuilder();		
            for (int i = 0; i < LONGITUD_NOMBRE; i++) {				// Para leer el string lo más fácil es ir caracter a caracter
                nombreBuilder.append(raf.readChar());
            }
            String nombre = nombreBuilder.toString().trim(); 		// trim() elimina caracteres nulos/espacios sobrantes
            
            double precio = raf.readDouble();
            boolean descuento = raf.readBoolean();
            char tipo = raf.readChar();

            Producto p2 = new Producto(id, nombre, precio, descuento, tipo);
            System.out.println("Registro " + nRegistro + " leído: " + p2);
		    
		 
		} catch (FileNotFoundException ex) {
		    System.out.println(ex.getMessage());
		} catch (IOException ex) {
		    System.out.println(ex.getMessage());
		}

	}

}
