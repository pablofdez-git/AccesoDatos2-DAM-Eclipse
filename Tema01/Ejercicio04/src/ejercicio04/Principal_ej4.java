package ejercicio04;

public class Principal_ej4 {

	public static void main(String[] args) {
		
		System.out.println("A - " + calcularColumna("A"));
        System.out.println("Z - " + calcularColumna("Z"));
        System.out.println("AA - " + calcularColumna("AA"));
        System.out.println("CA - " + calcularColumna("CA"));

	}
	
	public static int calcularColumna(String nombreColumna) {
		int resultado = 0;
		
		nombreColumna = nombreColumna.toUpperCase();
		
		for(int i = 0; i<nombreColumna.length();i++) {
			char letra = nombreColumna.charAt(i);
			
			int valorLetra = letra - 'A'  +1;
			
			resultado = resultado * 26 + valorLetra;
			
		}
		return resultado;
	}

}
