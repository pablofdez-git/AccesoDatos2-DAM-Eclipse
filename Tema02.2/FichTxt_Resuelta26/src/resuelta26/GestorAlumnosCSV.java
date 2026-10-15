package resuelta26;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorAlumnosCSV {
	
	//Importar la informacion del csv y guardarla en memoria (en este caso en una lista)
	public static ArrayList<Alumno> importar(String rutaFichero) {
		
		ArrayList<Alumno> alumnos = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(rutaFichero))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split(",");
                Alumno a = new Alumno(
                    partes[0].trim(),
                    Integer.parseInt(partes[1].trim()),
                    partes[2].trim(),
                    Double.parseDouble(partes[3].trim())
                );
                alumnos.add(a);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al procesar formato numérico: " + e.getMessage());
        }

        return alumnos;
        
    }
	
	//Buscar el alumno con mejor nota media
	public static Alumno buscarMejorAlumno(ArrayList<Alumno> alumnos) {
		if (alumnos == null || alumnos.isEmpty()) {
            return null;
        }

        Alumno mejor = alumnos.get(0);
        for (int i = 1; i < alumnos.size(); i++) {
            if (alumnos.get(i).getNotaMedia() > mejor.getNotaMedia()) {
                mejor = alumnos.get(i);
            }
        }
        return mejor;
    }
	
	
	// Filtrar alumnos aprobados
    public static ArrayList<Alumno> alumnosAprobados(ArrayList<Alumno> alumnos) {
        ArrayList<Alumno> aprobados = new ArrayList<>();
        if (alumnos != null) {
            for (Alumno alumno : alumnos) {
                if (alumno.getNotaMedia() >= 5.0) { 
                    aprobados.add(alumno);
                }
            }
        }
        return aprobados;
    }
    
    // Actualizar nota de alumno
    public static boolean actualizarAlumno(String nombre, double nota, String rutaFichero) {
		ArrayList<Alumno> alumnos = importar(rutaFichero);
		boolean encontrado = false;
		String respuesta;
		for(Alumno alumno : alumnos) {
			if(alumno.getNombre().equalsIgnoreCase(nombre.trim())) {
				alumno.setNotaMedia(nota);
				encontrado = true;
				break;
			}
		}
		
		if(encontrado) {
			exportarCSV(rutaFichero, alumnos);
		}
    	
    	return encontrado;
    }
    
	
	//Devolver formato csv
	public static String alumnoEnCSV(Alumno a) {
		String nombre = a.getNombre();
		int edad = a.getEdad();
		String cicloMatriculado = a.getCicloMatriculado();
		double notaMedia = a.getNotaMedia();
        String cadenaAlumnoFormatoCSV = nombre+","+edad+","+cicloMatriculado+","+notaMedia;
        return cadenaAlumnoFormatoCSV;
    }
	
	
	// Exportar CSV usando try-with-resources y el método toCSV() de Alumno
    public static void exportarCSV(String nombreFichero, ArrayList<Alumno> alumnos) {
        if (alumnos == null) return;

        try (BufferedWriter out = new BufferedWriter(new FileWriter(nombreFichero))) {
            for (Alumno alumno : alumnos) {
                out.write(alumno.toCSV());
                out.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
	


}
