package propuesta03;

import java.util.Locale;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		
		System.out.print("Introduce el nombre, la edad y la estatura en metros de un deportista: ");
		String nombre = sc.next();
		int edad = sc.nextInt();
		double altura = sc.nextDouble();
		
		System.out.println("--- Datos Deportista ---");
		System.out.println("Nombre: "+nombre);
		System.out.println("Edad: "+edad);
		System.out.println("Altura: "+altura);
		
		sc.close();

	}

}
