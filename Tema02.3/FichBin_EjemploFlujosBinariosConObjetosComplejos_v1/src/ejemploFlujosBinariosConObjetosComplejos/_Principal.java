package ejemploFlujosBinariosConObjetosComplejos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class _Principal {
	
	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\AidaRdgz\\workespaceAADD_ficherosBinarios\\FICHEROS\\socios.dat";
		
		//Creamos un array de socios de 4 posiciones y lo rellenamos
		Socio[] arraySocios = new Socio[4];
		arraySocios[0] = new Socio("1", "Pepe");	arraySocios[1] = new Socio("11", "Ana");
		arraySocios[2] = new Socio("7", "Juan");	arraySocios[3] = new Socio("23", "Alba");

		//Mostramos el array antes de guardarlo
		System.out.println("Inicial: " +Arrays.deepToString(arraySocios));

		//Creamos un flujo de salida binario para escribir en el
		try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
			salida.writeObject(arraySocios);
		} catch (IOException e) {
			System.out.println(e);
		}

		Socio[] arraySociosLeido = new Socio[4];	//nuevo array para recuperar la información guardada

		//Creamos un flujo de entrada binario y leemos de él el array de socios
		try(ObjectInputStream entrada= new ObjectInputStream(new FileInputStream(ruta))) {
			arraySociosLeido=(Socio[])entrada.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e);
		}

		System.out.println("Final:  " +Arrays.deepToString(arraySociosLeido));	//mostramos el array de socios leído del fichero

	}

}
