package ejercicio02;

import java.io.*;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Introduce el nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Introduce los apellidos: ");
		String apellidos = sc.nextLine();
		System.out.print("Introduce la edad: ");
		int edad = sc.nextInt();
		
		BufferedWriter out = null;
		
		try {
			// 'true' activa el modo append (añadir al final sin sobrescribir)
			out = new BufferedWriter(new FileWriter("C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\datos.txt", true));
			out.write(nombre + " "+ apellidos + " " + edad + " años");
			out.newLine();
			System.out.println("Ficha guardada correctamente");
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
			sc.close();
		}

	}

}
