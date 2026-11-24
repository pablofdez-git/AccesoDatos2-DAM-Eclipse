package fases;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Producto;
import persistencia.RepositorioInventario;

public class Fase9 {

	public static void main(String[] args) {
		
		List<Producto> productos = null;
		
		try {
			RepositorioInventario repo = new RepositorioInventario(Path.of("inventario.json"));
			productos = repo.cargar();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		if(productos != null) {
			JsonArray bajoStock = new JsonArray();
	        
	        for (Producto p : productos) {
	            if (p.getStock() < 20) {
	                JsonObject o = new JsonObject();
	                o.addProperty("nombre", p.getNombre());
	                o.addProperty("stock", p.getStock());
	                bajoStock.add(o);
	            }
	        }

	        JsonObject informe = new JsonObject();
	        informe.addProperty("generado", LocalDate.now().toString());
	        informe.addProperty("minimo", 20);
	        informe.add("productos", bajoStock);

	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        Path salida = Path.of("informe_stock_bajo.json");
	        
	        try (BufferedWriter w = Files.newBufferedWriter(salida, StandardCharsets.UTF_8)) {
	            gson.toJson(informe, w);
	        } catch (IOException e) {
				e.printStackTrace();
			}
	        
	        System.out.println("Informe generado con " + bajoStock.size() + " productos");
		}
        

	}
}
