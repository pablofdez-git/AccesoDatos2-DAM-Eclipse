package ejercicio2;

import java.util.*;

public class Ejercicio2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			double notas [] = new double[5];
			Random random = new Random();
			
			for(int i = 0; i<notas.length;i++) {
				notas[i] = random.nextDouble(10)+1;
			}
			
			System.out.println("Introduce una posicion para saber la nota del alumno 0-4");
			int pos = sc.nextInt();
			double nota = 0;
			
			for(int i = 0; i<notas.length;i++) {
				nota = notas[i];
			}
			
			System.out.println("La nota del alumno es "+nota);
		}catch(InputMismatchException e) {
			System.out.println("El numero no es un entero");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Posicion fuera del rango del array");
		}

	}

}