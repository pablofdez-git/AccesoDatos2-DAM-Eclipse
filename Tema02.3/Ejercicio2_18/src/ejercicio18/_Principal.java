package ejercicio18;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class _Principal {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\";
		
		ObjectInputStream flujoEntrada = null;
		ObjectOutputStream pares = null;
		ObjectOutputStream impares = null;
		
		try {
			flujoEntrada = new ObjectInputStream (new FileInputStream(ruta+"numeros.dat"));
			pares = new ObjectOutputStream(new FileOutputStream(ruta+"pares.dat"));
			impares = new ObjectOutputStream(new FileOutputStream(ruta+"impares.dat"));
			
			while(true) {
				int numero = flujoEntrada.readInt();
				if(numero % 2 == 0) {
					pares.writeInt(numero);
				}else {
					impares.writeInt(numero);
				}
			}
		} catch (EOFException e) { 
			System.out.println("Números separados correctamente."); 
		} catch (IOException e) {
			e.printStackTrace(); 
		}

	}

}
