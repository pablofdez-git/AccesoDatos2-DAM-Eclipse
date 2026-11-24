package fases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import modelo.Producto;
import persistencia.RepositorioInventarioJSON;
import persistencia.RepositorioInventarioXML;

public class Fase7 {

	public static void main(String[] args) {
		
		Path rutaJson = Path.of("inventario.json");
        Path rutaXml = Path.of("inventario_repo.xml");

        RepositorioInventarioJSON repoJson = new RepositorioInventarioJSON(rutaJson);
        RepositorioInventarioXML repoXml = new RepositorioInventarioXML(rutaXml);

        try {
            List<Producto> productos = repoJson.cargar();
            System.out.println("Cargados desde JSON: " + productos.size());

            repoXml.guardar(productos);
            System.out.println("Exportados a XML");

            List<Producto> deXml = repoXml.cargar();
            System.out.println("Leídos desde XML: " + deXml.size());
            for (Producto p : deXml) {
                System.out.println(p);
            }

            System.out.println("Tamaño JSON: " + Files.size(rutaJson) + " bytes");
            System.out.println("Tamaño XML:  " + Files.size(rutaXml) + " bytes");
        } catch (IOException e) {
            System.err.println("Error de persistencia: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Detalle: " + e.getCause().getMessage());
            }
        }
	}
}
