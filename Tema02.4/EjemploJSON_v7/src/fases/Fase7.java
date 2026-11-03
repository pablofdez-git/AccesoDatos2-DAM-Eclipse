package fases;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;
import persistencia.RepositorioInventario;

public class Fase7 {

	public static void main(String[] args) {
		
		RepositorioInventario repo = new RepositorioInventario(Path.of("inventario.json"));
        Scanner sc = new Scanner(System.in);
        try {
            List<Producto> productos = repo.cargar();
            int opcion;
            do {
                System.out.println("\n1. Listar   0. Salir");
                System.out.print("Opción: ");
                opcion = Integer.parseInt(sc.nextLine().trim());
                switch (opcion) {
                    case 1 -> {
                    	for (Producto p : productos) {
                    	    System.out.println(p);
                    	}
                    }
                    case 0 -> System.out.println("Hasta luego");
                    default -> System.out.println("Opción no implementada todavía");
                }
            } while (opcion != 0);
            repo.guardar(productos);
        } catch (IOException e) {
            System.err.println("Error de persistencia: " + e.getMessage());
        }

	}
}
