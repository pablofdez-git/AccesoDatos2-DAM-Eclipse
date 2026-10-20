package propuesta2_09;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce un numero entero: ");
		int entero = sc.nextInt();
		
		Double array[] = new Double[entero];
		
		for(int i = 0; i<entero;i++) {
			System.out.print("Introduce un decimal: ");
			double decimal = sc.nextDouble();
			array[i] = decimal;
		}
		
		System.out.println(Arrays.toString(array));
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\propuesta2_09.dat"));
			out.writeObject(array);
			System.out.println("Archivo creado");
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}

	}

}
