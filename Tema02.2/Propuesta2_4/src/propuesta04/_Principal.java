package propuesta04;

import java.io.*;
import java.util.Scanner;

public class _Principal {

	public static void main(String[] args) {
		
		String ruta = "C:\\Users\\Alumno\\OneDrive\\2ºDAM\\AccesoDatos\\varios\\NumerosReales";
		FileReader in = null;
		Scanner sc = null;
		double suma = 0.0;
		
		try {
			in = new FileReader(ruta);
			sc = new Scanner(in);
			
			while(sc.hasNext()) {
				suma = suma + sc.nextDouble();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(sc != null) {
				sc.close();
			}
		}
		
		System.out.println(suma);
		
		

	}

}
