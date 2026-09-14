package ejercicio02;

import java.util.Scanner;

public class Principal_ej2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Introduce el número que quieres invertir: ");
		int numOriginal = sc.nextInt();
		
		int numTemp = numOriginal;
		int numInvert = 0;
		
		while(numTemp != 0) {
			int digito = numTemp % 10;
			numInvert = numInvert * 10 + digito;
			numTemp = numTemp / 10;
		}
		
		System.out.println("El numero original sigue aqui: "+numOriginal);
		System.out.println("El numero invertido es: "+numInvert);

	}

}
