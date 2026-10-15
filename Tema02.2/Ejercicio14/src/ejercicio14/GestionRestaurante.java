package ejercicio14;

import java.io.*;
import java.util.*;

public class GestionRestaurante {
	
	// Importar la info del csv
	public static ArrayList<Restaurante> importar(String rutaFichero){
		ArrayList<Restaurante> restaurantes = new ArrayList<>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(rutaFichero));
			String linea;
			while ((linea = in.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("#");
                Restaurante r = new Restaurante(
                		partes[0].trim(),
                		partes[1].trim(), partes[2].trim(),
                		Double.parseDouble(partes[3].trim()),
                		partes[4].trim(),
                		partes[5].trim());
                restaurantes.add(r);
            }
			
		} catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al procesar formato numérico: " + e.getMessage());
        }
		
		return restaurantes;
	}
	
	public static Restaurante leerPorTelefono(ArrayList<Restaurante> restaurantes, String telefono) {
		if (restaurantes != null || !restaurantes.isEmpty()) {
			for(Restaurante r : restaurantes) {
				if(r.getTelefono().trim().equalsIgnoreCase(telefono.trim())) {
					return r;
				}
			}
		}
		return null;
	}
	
	public static Restaurante leerPorCiudad(ArrayList<Restaurante> restaurantes, String ciudad) {
		if(restaurantes != null || !restaurantes.isEmpty()) {
			for(Restaurante r : restaurantes) {
				if(r.getCiudad().trim().equalsIgnoreCase(ciudad.trim())) {
					return r;
				}
			}
		}
		return null;
	}
	
	
	

}
