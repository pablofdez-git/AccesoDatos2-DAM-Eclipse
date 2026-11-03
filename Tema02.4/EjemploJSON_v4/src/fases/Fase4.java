package fases;

import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

public class Fase4 {

	public static void main(String[] args) {
		
		/*
		 * 	SERIALIZAR (Pasar de Java a JSON)
		 */
		Producto p = new Producto(1, "Taladro percutor", 89.90, 12, Categoria.HERRAMIENTA, LocalDate.of(2026, 10, 13),
                new Proveedor("Ferrosur", "pedidos@ferrosur.example"), List.of("electrico", "obra"));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(p));

	}

}
