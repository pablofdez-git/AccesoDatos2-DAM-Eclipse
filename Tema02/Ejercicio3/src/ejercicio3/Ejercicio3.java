package ejercicio3;

public class Ejercicio3 {

	public static void main(String[] args) {
		
		String array [] = {"15","12","H","34","65","21","Hola","46","Casa","86","39"};
		
		int suma = 0;
		int contador = 0;
		
		for(int i = 0; i<array.length;i++) {
			try {
				int edad = Integer.parseInt(array[i]);
				suma += edad;
				contador++;
			}catch(NumberFormatException e) {
				System.out.println("Se ha ignorado el valor: "+array[i]);
			}
		}
		if(contador == 0) {
			System.out.println("No hay ningun numero que tratar");
		}else {
			double media = suma / contador;
			System.out.println("La suma de las edades es de: "+suma);
			System.out.println("La media es de: "+media);
		}
		

	}

}
