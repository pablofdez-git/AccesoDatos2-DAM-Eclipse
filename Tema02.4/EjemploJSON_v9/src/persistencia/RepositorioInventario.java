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

public class RepositorioInventario {
	
	private static final Type TIPO_LISTA = new TypeToken<List<Producto>>() {}.getType();
    private final Path fichero;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter().nullSafe()).create();

    public RepositorioInventario(Path fichero) {
        this.fichero = fichero;
    }

    public List<Producto> cargar() throws IOException {
        if (Files.notExists(fichero)) {
            return new ArrayList<>();
        }
        try (Reader r = Files.newBufferedReader(fichero, StandardCharsets.UTF_8)) {
            List<Producto> lista = gson.fromJson(r, TIPO_LISTA);
            return lista != null ? lista : new ArrayList<>();
        } catch (JsonParseException e) {
            throw new IOException("Inventario corrupto: " + fichero, e);
        }
    }

    public void guardar(List<Producto> productos) throws IOException {
        Files.createDirectories(fichero.toAbsolutePath().getParent());
        Path tmp = fichero.resolveSibling(fichero.getFileName() + ".tmp");
        try (Writer w = Files.newBufferedWriter(tmp, StandardCharsets.UTF_8)) {
            gson.toJson(productos, TIPO_LISTA, w);
        }
        Files.move(tmp, fichero, StandardCopyOption.REPLACE_EXISTING);
    }


}
