package ejemploObjectInputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class _Principal {
	
	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\AidaRdgz\\workespaceAADD_ficherosBinarios\\FICHEROS\\datosArray.dat";
		
		try(ObjectInputStream flujoEntrada = new ObjectInputStream (new FileInputStream (ruta))){
			double[] array = (double[])flujoEntrada.readObject();			//Lee el objeto, en este caso un array
			System.out.println(Arrays.toString(array));						//Muestra por pantalla el array
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Error de lectura");	
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			System.out.println("El fichero no almacena un objeto de tipo array");	
		}

	}

}
