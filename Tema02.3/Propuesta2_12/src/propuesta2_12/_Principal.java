package propuesta2_12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class _Principal {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\cancionPirata.dat";
		String cancion = null;
		try(ObjectInputStream flujoEntrada = new ObjectInputStream (new FileInputStream(ruta))){
			cancion = (String) flujoEntrada.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(cancion);

	}

}
