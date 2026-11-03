package fases;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

import modelo.Producto;

public class Fase2_Leer {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
        Path ruta = Path.of("productoGuardado.json");

        try (Reader r = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            Producto p = gson.fromJson(r, Producto.class);
            System.out.println("Leído: " + p);
        } catch (IOException e) {
			e.printStackTrace();
		}


	}

}
