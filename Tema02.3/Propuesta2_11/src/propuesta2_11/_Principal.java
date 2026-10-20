package propuesta2_11;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class _Principal {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\datos.dat";
		int[] array = new int[10];
		try(ObjectInputStream flujoEntrada = new ObjectInputStream (new FileInputStream(ruta))){
			for(int i = 0; i<array.length;i++) {
				array[i] = flujoEntrada.readInt();
			}
			System.out.println(Arrays.toString(array));
		}catch(IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Error de lectura");
		}

	}

}
