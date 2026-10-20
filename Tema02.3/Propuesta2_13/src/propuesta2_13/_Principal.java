package propuesta2_13;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\";
		Scanner sc = new Scanner(System.in);
		ObjectOutputStream flujoSalida = null;
		try {
			flujoSalida = new ObjectOutputStream(new FileOutputStream(ruta+"numeros.dat"));
			int num = 0;
			do {
				System.out.print("Introduce un numero no negativo: ");
				num = sc.nextInt();
				if(num != -1) {
					flujoSalida.write(num);
				}
			}while(num != -1);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(flujoSalida != null) {
				try {
					flujoSalida.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		
		ObjectInputStream flujoEntrada = null;
		ObjectOutputStream salida = null;
		
		try {
			flujoEntrada = new ObjectInputStream (new FileInputStream(ruta+"numeros.dat"));
			salida = new ObjectOutputStream(new FileOutputStream(ruta+"numerosCopia.dat"));
			System.out.print("[");
			
			while(true) {
				int numero = flujoEntrada.readInt();
				System.out.println(numero + " ");
				salida.writeInt(numero);
			}
		}catch(EOFException ex) {
			System.out.print("]");
			System.out.print("Fin de fichero");
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
		

	}

}
