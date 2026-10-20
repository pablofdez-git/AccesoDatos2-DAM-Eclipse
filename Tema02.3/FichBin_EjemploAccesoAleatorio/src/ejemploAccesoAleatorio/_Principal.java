package ejemploAccesoAleatorio;

import java.io.IOException;
import java.io.RandomAccessFile;

public class _Principal {
	
	public static void main(String[] args) {
		
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
