package ejemploObjectOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class _Principal {
	
	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\datos.dat";
		
		int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};			//inicializar el array
		
		ObjectOutputStream flujoSalida = null;					//crear el flujo de salida

		try {
			flujoSalida = new ObjectOutputStream(new FileOutputStream(ruta));

			for (int n: array ) {						//escribir los datos numero a numero en el archivo
				flujoSalida.writeInt(n);		
			}
			
			//flujoSalida.writeObject(array);			//se puede escribir el array como objeto completo

		} catch (IOException e) {
			System.out.print(e);
		} finally {
			if(flujoSalida != null) {
				try {
					flujoSalida.close();		//cerrar flujo salida
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

	}

}
