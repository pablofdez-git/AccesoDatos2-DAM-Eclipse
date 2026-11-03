package fases;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Producto;

public class Fase3_Guardar {

	public static void main(String[] args) {
		
		List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Taladro percutor", 89.90, 12));
        productos.add(new Producto(2, "Martillo", 12.50, 40));
        productos.add(new Producto(3, "Cinta métrica 5 m", 6.75, 25));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Path ruta = Path.of("productosGuardado.json");

        try (Writer w = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            gson.toJson(productos, w);
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.println("Guardados " + productos.size() + " productos");

	}

}
