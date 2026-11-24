package persistencia;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import modelo.Producto;

/*
 * Clase encargada de la persistencia de datos en formato JSON.
 * Aplica el Patrón Repositorio aislando la lectura y escritura en fichero del resto de la lógica de negocio de la aplicación.
 */
public class RepositorioInventarioJSON {
	
	// Solución al borrado de genéricos en Java (Type Erasure).
    // 		Permite que GSON sepa en tiempo de ejecución que el JSON debe convertirse en un List<Producto>.
	private static final Type TIPO_LISTA = new TypeToken<List<Producto>>(){}.getType();
    
	// Ruta abstracta al fichero en el sistema de archivos
	private final Path fichero;
	
	// Instancia única y reutilizable de Gson.
    // Configurada con formato legible (.setPrettyPrinting()) y el adaptador para LocalDate.
    private final Gson gson = new GsonBuilder()
    		.setPrettyPrinting()
    		.registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe())
    		.create();

    // CONSTRUCTOR
    public RepositorioInventarioJSON(Path fichero) {
        this.fichero = fichero;
    }

    
    // Carga la lista de productos desde el fichero JSON.
    public List<Producto> cargar() throws IOException {
    	// Si el archivo aún no existe (p. ej. primer arranque), devuelve una lista vacía sin fallar.
        if (Files.notExists(fichero)) {
            return new ArrayList<>();
        }
       
        // Se especifica StandardCharsets.UTF_8 para evitar problemas con acentos o caracteres especiales.
        try (Reader r = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)) {
            List<Producto> lista = gson.fromJson(r, TIPO_LISTA);
         // Si el fichero está vacío (0 bytes), gson.fromJson devuelve null; por eso se devuelve ArrayList vacía.
            return lista != null ? lista : new ArrayList<>();
        } catch (JsonParseException e) {
        	// Captura las excepciones de sintaxis de GSON (Runtime) y las traduce a IOException
            // para avisar a la aplicación de que el archivo está corrupto sin detener la ejecución de golpe.
            throw new IOException("Inventario corrupto: " + fichero, e);
        }
    }

    
    
    // Guarda la lista actual de productos en el fichero JSON.
    //		Aplica la técnica de escritura atómica (fichero temporal + reemplazo)
    public void guardar(List<Producto> productos) throws IOException {
    	// Asegura que las carpetas contenedoras existan en el sistema si la ruta incluye directorios.
        if (fichero.toAbsolutePath().getParent() != null) {
            Files.createDirectories(fichero.toAbsolutePath().getParent());
        }
        
        // Crea una ruta para un archivo temporal (ej. inventario.json.tmp en la misma carpeta)
        Path tmp = fichero.resolveSibling(fichero.getFileName() + ".tmp");
        
        // Escritura en el archivo temporal usando UTF-8
        try (Writer w = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)) {
            gson.toJson(productos, TIPO_LISTA, w);
        }
        // Reemplaza de forma atómica el archivo original por el archivo temporal.
        // Si el proceso falla a mitad de escritura, el fichero original permanece intacto sin corromperse.
        Files.move(tmp, fichero, StandardCopyOption.REPLACE_EXISTING);
    }


}
