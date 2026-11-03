package fases;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Producto;

public class Fase3_Leer_KO {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
        Path ruta = Path.of("productosGuardado.json");

        try (Reader r = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
        	List<Producto> productos = gson.fromJson(r, List.class);
            System.out.println("Cargados " + productos.size());
            Producto primero = productos.get(0);
            System.out.println(primero.getNombre());
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

}
