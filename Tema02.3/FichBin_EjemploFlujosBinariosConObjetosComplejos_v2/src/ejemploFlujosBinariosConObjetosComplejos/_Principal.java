package ejemploFlujosBinariosConObjetosComplejos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class _Principal {
	
	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\AidaRdgz\\workespaceAADD_ficherosBinarios\\FICHEROS\\socios_v2.dat");
		
		//Creamos un array de socios de 4 posiciones y lo rellenamos
		ArrayList<Socio> listaSocios = new ArrayList<>();
		listaSocios.add(new Socio("1", "Pepe"));	listaSocios.add(new Socio("11", "Ana"));
		listaSocios.add(new Socio("7", "Juan"));	listaSocios.add(new Socio("23", "Alba"));

		//Mostramos el array antes de guardarlo
		System.out.println("Inicial: " +listaSocios.toString());

		//Escribir de objeto en objeto (hay que evitar reescribir la cabecera de control)

		//Creamos un flujo de salida binario para escribir en el
		ObjectOutputStream salida;
		try {
			
			if (file.exists()) {		// Si el archivo ya existe se activa append. Hay que quitar la cabecera a la nueva escritura
				salida = new AppendableObjectOutputStream(new FileOutputStream(file, true));
			} else {					// Si el archivo no existe se crea con un flujo normal
				salida = new ObjectOutputStream(new FileOutputStream(file));
			}
			
			for(Socio s : listaSocios) {
				salida.writeObject(s);			//Escribir socio a socio
			}
			
			salida.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
		
		//Creamos un flujo de entrada binario y leemos de él el array de socios
		ArrayList<Socio> listaSociosLeida = new ArrayList<>();
		
		try(ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(file))) {
			while (true) {
				listaSociosLeida.add((Socio)entrada.readObject());		//leer socio a socio, tal como fueron guardados
		    }
			
			
		} catch (EOFException e) {
            System.out.println("Fin del archivo alcanzado.");
		
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}

		System.out.println("Final:  " +listaSociosLeida.toString());	//mostramos el array de socios leído del fichero

	}

}
