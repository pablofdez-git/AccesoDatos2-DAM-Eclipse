package fases;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import modelo.Producto;

public class Fase3_Leer_OK {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
        Path ruta = Path.of("productosGuardado.json");
        Type tipoLista = new TypeToken<List<Producto>>() {}.getType();

        try (Reader r = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
        	List<Producto> productos = gson.fromJson(r, tipoLista);

        	// Mostrar todos los productos
        	for (Producto p : productos) {
        	    System.out.println(p);
        	}

        	// Calcular el valor total del inventario
        	double valorTotal = 0;

        	for (Producto p : productos) {
        	    valorTotal += p.getPrecio() * p.getStock();
        	}

        	System.out.printf("Valor del inventario: %.2f €%n", valorTotal);

        	// Mostrar los productos que necesitan reposición
        	for (Producto p : productos) {
        	    if (p.getStock() < 20) {
        	        System.out.println("Reponer: " + p.getNombre());
        	    }
        	}
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

}
