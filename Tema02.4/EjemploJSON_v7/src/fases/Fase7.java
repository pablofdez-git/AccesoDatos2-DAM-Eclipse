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
            	System.out.println("Menu Inventario Taller");
                System.out.println("1. Listar \n2. Alta Producto \n3. Registrar una venta \n4. Registrar entrada mercancia \n5. Baja Producto"
                		+ "\n6. Informe reposicion \n7. Salir");
                System.out.print("Opción: ");
                opcion = Integer.parseInt(sc.nextLine().trim());
                switch (opcion) {
                    case 1 -> {
                    	System.out.println("==== Lista de los productos ====");
                    	for (Producto p : productos) {
                    	    System.out.println(p);
                    	}
                    }
                    case 2 -> {
                    	System.out.println("==== Alta de un producto ====");
                    	int idNuevo = 0;
                    	System.out.print("Introduce el nombre: ");
                    	String nombreNuevo = sc.next();
                    	System.out.print("Introduce el precio: ");
                    	double precioNuevo = sc.nextDouble();
                    	while(precioNuevo < 0) {
                    		System.out.println("El precio tiene que ser superior a 0");
                    		System.out.println("Introduce el precio: ");
                    		precioNuevo = sc.nextDouble();
                    	}
                    	System.out.print("Introduce el stock: ");
                    	int stockNuevo = sc.nextInt();
                    	while(stockNuevo < 0) {
                    		System.out.println("El stock tiene que ser superior a 0");
                    		System.out.println("Introduce el stock: ");
                    		stockNuevo = sc.nextInt();
                    	}
                    	System.out.println("Categorías disponibles: HERRAMIENTA, ELECTRONICA, CONSUMIBLE");
                        System.out.print("Introduce la categoría: ");
                        Categoria categoriaNueva = Categoria.valueOf(sc.nextLine().trim().toUpperCase());
                        
                        System.out.println("¿Quieres introducir un proveedor? Si/No");
                        String respuesta = sc.next();
                        String proveedorNuevo =  null;
                        if(respuesta.equalsIgnoreCase(respuesta)) {
                        	System.out.println("Introduce el nombre del proveedor: ");
                        	proveedorNuevo = sc.next();
                        }
                        
                    }
                    case 3 -> {
                    	
                    }
                    case 4 -> {
                    	
                    }
                    case 5 -> {
                    	
                    }
                    case 6 -> {
                    	
                    }
                    case 7 -> System.out.println("Hasta luego");
                    default -> System.out.println("Opción no implementada todavía");
                }
            } while (opcion != 0);
            repo.guardar(productos);
        } catch (IOException e) {
            System.err.println("Error de persistencia: " + e.getMessage());
        }

	}
}
