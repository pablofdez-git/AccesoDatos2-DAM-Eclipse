package ejemploAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;

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
		//final int TAMANO_REGISTRO = 4 + (LONGITUD_NOMBRE *2)
		
		String ruta = "C:\\Users\\AidaRdgz\\workespaceAADD_ficherosBinarios\\FICHEROS\\random.dat";
		
		try {
			RandomAccessFile raf = new RandomAccessFile(ruta, "rw");

			for(int i=15646555; i<15646555+10; i++) {
				raf.writeInt(i);
			}

			raf.seek (5 * 4);
			int sexto = raf.readInt();
			System.out.println("El sexto entero es: " +sexto);
			
			raf.close();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
