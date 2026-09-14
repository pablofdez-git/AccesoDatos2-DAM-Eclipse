package ejercicio03;

public class Principal_ej3 {

	public static void main(String[] args) {

		int  numeros [] = {6,9,2,8,6,4,7};
		
		int maximo = Integer.MIN_VALUE;
		int segMax = Integer.MIN_VALUE;
		
		for(int numero : numeros) {
			if(numero > maximo) {
				segMax = maximo;
				maximo = numero;
			}else if (numero > segMax && numero != maximo) {
				segMax = numero;
			}
		}
		
		if(segMax != Integer.MIN_VALUE) {
			System.out.println("El segundo mas grande es: "+segMax);
		}
		
	}

}
