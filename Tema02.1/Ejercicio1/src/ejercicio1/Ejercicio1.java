package ejercicio1;

import java.util.*;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int operador = 0;
		do {
			try {
				System.out.print("Introduce un numero entero: ");
				int numA = sc.nextInt();
				System.out.print("Introduce otro numero entero: ");
				int numB = sc.nextInt();
				
				System.out.println("Que operacion quieres hacer: ");
				System.out.println("1. Suma | 2. Resta | 3. Multiplicacion | 4. Division | -1 Salir");
				operador = sc.nextInt();
				
				double resultado = 0;
				switch(operador){
					case 1:
						resultado = numA + numB;
					break;
					case 2:
						if(numA >= numB) {
							resultado = numA - numB;
						}else {
							resultado = numB - numA;
						}
					break;
					case 3:
						resultado = numA * numB;
					break;
					case 4:
						resultado = numA / numB;
					break;
				}
				
				System.out.println("El resultado es: "+resultado);
			}catch(InputMismatchException e) {
				System.out.println("Solo se pueden introducir numeros");
			}catch(ArithmeticException e) {
				System.out.println("No se puede dividir entre 0");
			}
			
		}while(operador != -1);
	}

}