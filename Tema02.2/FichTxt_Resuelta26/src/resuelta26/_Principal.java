package resuelta26;

import java.util.ArrayList;

public class _Principal {

	public static void main(String[] args) {
		
		String rutaFicheroOrigen = "C:\\Users\\AidaRdgz\\workespaceAADD_ficherosTexto\\FICHEROS\\alumnos.csv";
		String rutaFicheroAprobados = "C:\\Users\\AidaRdgz\\workespaceAADD_ficherosTexto\\FICHEROS\\alumnosAprobados.csv";
		
		//Imprimir por pantalla todos los alumnos
		ArrayList<Alumno> listaAlumnos;					//variable que almacenara la lista de alumnos devuelta por el método
		listaAlumnos = GestorAlumnosCSV.importar(rutaFicheroOrigen);
		
		for(Alumno a : listaAlumnos) {
			System.out.println(a);
		}
		
		System.out.println("");
		
		//Mostrar alumno con mejor media
		Alumno mejorAlumno = GestorAlumnosCSV.buscarMejorAlumno(listaAlumnos);
		System.out.println("El alumno con mejor nota media es: " +mejorAlumno);
		
		System.out.println("");
		
		//Crear fichero solo con alumnos con media aprobada
		GestorAlumnosCSV.exportarCSV(rutaFicheroAprobados, GestorAlumnosCSV.alumnosAprobados(listaAlumnos));

	}

}
