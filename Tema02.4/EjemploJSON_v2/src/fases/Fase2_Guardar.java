package fases;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Producto;

public class Fase2_Guardar {

	public static void main(String[] args) {
		
		Producto p = new Producto(1, "Taladro percutor", 89.90, 12);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path ruta = Path.of("productoGuardado.json");

        try (Writer w = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            gson.toJson(p, w);
        } catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.println("Guardado en " + ruta.toAbsolutePath());



	}

}
