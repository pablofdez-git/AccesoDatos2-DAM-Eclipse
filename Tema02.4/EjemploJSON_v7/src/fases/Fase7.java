package fases;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
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
            List<Producto> productos;
            try {
            	productos = repo.cargar();
            }catch(DateTimeParseException e) {
            	throw new IOException("Inventario corrupto: " + e.getMessage(), e);
            }
            validar(productos);
            
            int siguienteId = 1;
            for (Producto p : productos) {
                if (p.getId() >= siguienteId) {
                    siguienteId = p.getId() + 1;
                }
            }
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
                    	String nombreNuevo = introduceNombre(sc);
                        double precioNuevo = introducePrecio(sc);
                        int stockNuevo = introduceStock(sc);
                        Categoria categoriaNueva = introduceCategoria(sc);
                        Proveedor proveedorNuevo = introduceProveedor(sc);
                        List<String> etiquetas = etiquetas(sc);
                        Producto nuevo = new Producto(siguienteId, nombreNuevo, precioNuevo, stockNuevo,
                                categoriaNueva, LocalDate.now(), proveedorNuevo, etiquetas);
                        productos.add(nuevo);
                        siguienteId++;

                        System.out.println("Producto dado de alta: " + nuevo);
                        
                    }
                    case 3 -> {
                    	System.out.println("==== Registrar una venta ====");
                    	int idVenta = introduceId(sc);
                    	int stockVenta = introduceCantidad(sc);
                    	
                    	boolean encontrado = false;
                    	for(Producto p : productos) {
                    		if(p.getId() == idVenta) {
                    			encontrado = true;
                    			if(p.getStock() - stockVenta >= 0) {
                    				p.setStock(p.getStock() - stockVenta);
                    				System.out.println("Venta Registrada. Stock actual de "+ p.getNombre()+" : "+p.getStock());
                    			}else {
                    				System.out.println("Stock insuficiente. Solo hay " + p.getStock() + " unidades");
                    			}
                    			
                    		}
                    	}
                    	if(!encontrado) {
                    		System.out.println("No existe ningun producto con id " + idVenta);
                    	}
                    	
                    	
                    }
                    case 4 -> {
                    	System.out.println("==== Entrada de Mercancia ====");
                    	int idEntrada = introduceId(sc);
                    	int stockEntrada = introduceCantidad(sc);
                    	
                    	boolean encontrado = false;
                    	for(Producto p : productos) {
                    		if(p.getId() == idEntrada) {
                    			encontrado = true;
                    			p.setStock(p.getStock() + stockEntrada);
                    			System.out.println("Entrada Registrada. Stock actual de "+p.getNombre()+" : "+p.getStock());
                    		}
                    		
                    	}
                    	if(!encontrado) {
                    		System.out.println("No existe ningun producto con id "+idEntrada);
                    	}
                    	
                    }
                    case 5 -> {
                    	System.out.println("==== Baja de un producto ====");
                    	int idBaja = introduceId(sc);
                    	boolean encontrado = false;
                    	Iterator<Producto> it = productos.iterator();
                        while (it.hasNext()) {
                            Producto p = it.next();
                            if (p.getId() == idBaja) {
                                it.remove();
                                encontrado = true;
                                System.out.println("Producto " + p.getNombre() + " (id " + idBaja + ") dado de baja");
                            }
                        }
                    	if(!encontrado) {
                    		System.out.println("No existe ningun producto con id "+idBaja);
                    	}
                    }
                    case 6 -> {
                    	System.out.println("==== Informe de Reposicion ====");
                    	int stockMin = introduceStockMinimo(sc);
                    	
                    	boolean hayAlguno = false;
                    	for(Producto p : productos) {
                    		if(p.getStock() < stockMin) {
                    			hayAlguno = true;
                    			System.out.println("Producto "+p.getId()+"-> Nombre: "+p.getNombre()+" Stock: "+p.getStock()
                    					+ " Precio: "+p.getPrecio()+" Categoria: "+p.getCategoria());
                    		}
                    	}
                    	if (!hayAlguno) {
                            System.out.println("Ningún producto tiene stock por debajo de " + stockMin);
                        }

                    }
                    
                    case 7 -> System.out.println("Hasta luego");
                    default -> System.out.println("Opción no implementada todavía");
                }
            } while (opcion != 7);
            repo.guardar(productos);
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
            System.err.println("No se ha guardado nada: inventario.json se queda como estaba.");
            System.err.println("Revisa el fichero (o bórralo para empezar de cero) y vuelve a ejecutar.");
        }

	}
	
	//---------------------------------------------------------------------------------------------------
	public static String introduceNombre(Scanner sc) {
		System.out.print("Introduce el nombre: ");
        String nombre = sc.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío");
            System.out.print("Introduce el nombre: ");
            nombre = sc.nextLine().trim();
        }
        return nombre;
	}
	
	public static double introducePrecio(Scanner sc) {
		System.out.print("Introduce el precio: ");
        double precio = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
        while (precio <= 0) {
            System.out.println("El precio tiene que ser mayor que 0");
            System.out.print("Introduce el precio: ");
            precio = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
        }
        return precio;
	}
	
	public static int introduceStock(Scanner sc) {
		System.out.print("Introduce el stock: ");
        int stock = Integer.parseInt(sc.nextLine().trim());
        while (stock < 0) {
            System.out.println("El stock no puede ser negativo");
            System.out.print("Introduce el stock: ");
            stock = Integer.parseInt(sc.nextLine().trim());
        }
        return stock;
	}
	
	public static Categoria introduceCategoria(Scanner sc) {
		Categoria categoria = null;
        while (categoria == null) {
            System.out.println("Categorías disponibles: HERRAMIENTA, ELECTRONICA, CONSUMIBLE");
            System.out.print("Introduce la categoría: ");
            try {
                categoria = Categoria.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Categoría no válida");
            }
        }
        return categoria;
	}
	
	public static Proveedor introduceProveedor(Scanner sc) {
		Proveedor proveedor = null;
        System.out.print("¿Quieres introducir un proveedor? Si/No: ");
        String respuesta = sc.nextLine().trim();
        if (respuesta.equalsIgnoreCase("si")) {
            System.out.print("Introduce el nombre del proveedor: ");
            String nombreProv = sc.nextLine().trim();
            System.out.print("Introduce el email del proveedor: ");
            String emailProv = sc.nextLine().trim();
            proveedor = new Proveedor(nombreProv, emailProv);
        }
        return proveedor;
	}
	
	public static List<String> etiquetas(Scanner sc) {
		System.out.print("Etiquetas separadas por comas (Enter para ninguna): ");
        List<String> etiquetas = new ArrayList<>();
        for (String e : sc.nextLine().split(",")) {
            if (!e.trim().isEmpty()) {
                etiquetas.add(e.trim().toLowerCase());
            }
        }
        return etiquetas;
	}
	
	public static int introduceId(Scanner sc) {
	    while (true) {
	        System.out.print("Introduce el id: ");
	        try {
	            int id = Integer.parseInt(sc.nextLine().trim());
	            if (id > 0) {
	                return id;
	            }
	            System.out.println("El id tiene que ser mayor que 0");
	        } catch (NumberFormatException e) {
	            System.out.println("Tienes que escribir un número entero");
	        }
	    }
	}

	public static int introduceCantidad(Scanner sc) {
	    while (true) {
	        System.out.print("Introduce la cantidad: ");
	        try {
	            int cantidad = Integer.parseInt(sc.nextLine().trim());
	            if (cantidad > 0) {
	                return cantidad;
	            }
	            System.out.println("La cantidad tiene que ser mayor que 0");
	        } catch (NumberFormatException e) {
	            System.out.println("Tienes que escribir un número entero");
	        }
	    }
	}
	public static int introduceStockMinimo(Scanner sc) {
	    while (true) {
	        System.out.print("Introduce el stock minimo: ");
	        try {
	            int minimo = Integer.parseInt(sc.nextLine().trim());
	            if (minimo > 0) {
	                return minimo;
	            }
	            System.out.println("El stock minimo tiene que ser mayor que 0");
	        } catch (NumberFormatException e) {
	            System.out.println("Tienes que escribir un número entero");
	        }
	    }
	}
	public static void validar(List<Producto> productos) throws IOException {
	    for (Producto p : productos) {
	        if (p == null) {
	            throw new IOException("Inventario corrupto: hay un producto vacío");
	        }
	        if (p.getNombre() == null || p.getNombre().isBlank()) {
	            throw new IOException("Inventario corrupto: el producto #" + p.getId() + " no tiene nombre");
	        }
	        if (p.getStock() < 0) {
	            throw new IOException("Inventario corrupto: el producto #" + p.getId()
	                    + " tiene stock negativo (" + p.getStock() + ")");
	        }
	        if (p.getPrecio() <= 0) {
	            throw new IOException("Inventario corrupto: el producto #" + p.getId()
	                    + " tiene precio no válido (" + p.getPrecio() + ")");
	        }
	    }
	}
}
