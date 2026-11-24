package ejecutable;

import java.io.File;
import java.util.List;

import modelo.Articulo;
import persistencia.RepositorioCatalogoXML;

public class Principal {

	public static void main(String[] args) throws Exception {
		
		RepositorioCatalogoXML catalogo = new RepositorioCatalogoXML();

        // 1) Leer el catálogo del proveedor
        List<Articulo> articulos = catalogo.cargar(new File("catalogo.xml"));
        System.out.println("Proveedor: " + catalogo.getProveedor());
        for (Articulo a : articulos) {
            System.out.println(a);
        }

        // 2) Subir un 10 % el precio de los artículos eléctricos
        for (Articulo a : articulos) {
            if (a.getCategoria().equals("ELECTRICO")) {
                double nuevo = Math.round(a.getPvp() * 1.10 * 100) / 100.0;
                a.setPvp(nuevo);
            }
        }

        // 3) Añadir un artículo nuevo
        articulos.add(new Articulo(301, "Alicates universales", "HERRAMIENTA", 9.75));

        // 4) Guardar el resultado en otro fichero
        catalogo.guardar(articulos, new File("catalogo_actualizado.xml"));

        System.out.println();
        System.out.println("Catálogo actualizado:");
        for (Articulo a : articulos) {
            System.out.println(a);
        }
        System.out.println("\nGuardado en catalogo_actualizado.xml");

	}

}
