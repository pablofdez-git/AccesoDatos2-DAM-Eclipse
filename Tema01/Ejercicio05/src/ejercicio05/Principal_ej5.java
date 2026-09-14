package ejercicio05;

public class Principal_ej5 {

	public static void main(String[] args) {
		
		int array [] = {23,6,45,67,87,34,23,65,76,2,65,67,86};
		int contPares = 0;
		int contImpar = 0;
		int mayor = Integer.MIN_VALUE;
		int menor = Integer.MAX_VALUE;
		
		for(int i = 0; i< array.length;i++) {
			if(array[i] % 2 == 0) {
				contPares++;
			}else {
				contImpar++;
			}
			
			if(array[i] > mayor) {
				mayor = array[i];
			}
			
			if(array[i] < menor) {
				menor = array[i];
			}
		}
		
		System.out.println("Resultados finales:");
        System.out.println("Pares encontrados: " + contPares);
        System.out.println("Impares encontrados: " + contImpar);
        System.out.println("El número mayor es: " + mayor);
        System.out.println("El número menor es: " + menor);

	}

}
