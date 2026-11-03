package fases;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;
import persistencia.LocalDateAdapter;

public class Fase5 {

	public static void main(String[] args) {
		
		/*
		 * 	SERIALIZAR (Pasar de Java a JSON)
		 */
		Producto p = new Producto(1, "Taladro percutor", 89.90, 12, Categoria.HERRAMIENTA, LocalDate.of(2026, 10, 13),
                new Proveedor("Ferrosur", "pedidos@ferrosur.example"), List.of("electrico", "obra"));

		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe()).create();

		String json = gson.toJson(p);
        System.out.println(json);
        
        /*
         * 	Usar el campo como localDate aunque se haya dado formato con un adaptador (se puede operar con el, no es solo texto)
         */
        Producto copia = gson.fromJson(json, Producto.class);
        System.out.println(copia);
        System.out.println(copia.getFechaAlta().plusDays(30)); 

	}

}
