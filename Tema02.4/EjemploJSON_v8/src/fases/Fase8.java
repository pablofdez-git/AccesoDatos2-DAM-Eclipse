package fases;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Fase8 {

	public static void main(String[] args) {
		
		Path ruta = Path.of("inventario.json");
		
		try (Reader r = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
			JsonArray productos = JsonParser.parseReader(r).getAsJsonArray();
			for (JsonElement elemento : productos) {
					JsonObject o = elemento.getAsJsonObject();
					
					String nombre = o.get("nombre").getAsString();
					int stock = o.has("stock") ? o.get("stock").getAsInt() : 0;
					boolean tieneProveedor = o.has("proveedor");
					
					System.out.println(nombre + " -> stock: " + stock + (tieneProveedor ? "" : " (sin proveedor)"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
