package fases;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;
import persistencia.RepositorioInventario;

public class Fase6 {

	public static void main(String[] args) {
		
		RepositorioInventario repo = new RepositorioInventario(Path.of("inventario.json"));
        try {
            List<Producto> productos = repo.cargar();

            if (productos.isEmpty()) {
                System.out.println("Primer arranque: creo el inventario inicial");
                productos.add(new Producto(1, "Taladro percutor", 89.90, 12,
                        Categoria.HERRAMIENTA, LocalDate.now(),
                        new Proveedor("Ferrosur", "pedidos@ferrosur.example"),
                        List.of("electrico", "obra")));
                productos.add(new Producto(2, "Disco de corte 115 mm", 1.95, 200,
                        Categoria.CONSUMIBLE, LocalDate.now(),
                        new Proveedor("Abrasivos Norte", "ventas@abrasivosnorte.example"),
                        List.of("amoladora")));
                productos.add(new Producto(3, "Polímetro digital", 24.50, 8,
                        Categoria.ELECTRONICA, LocalDate.now(), null, List.of()));
            }

            Producto vendido = productos.get(0);
            if (vendido.getStock() > 0) {
                vendido.setStock(vendido.getStock() - 1);
            }

            System.out.println("Inventario:");
            productos.forEach(System.out::println);

            repo.guardar(productos);
        } catch (IOException e) {
            System.err.println("Error de persistencia: " + e.getMessage());
        }
	}
}
