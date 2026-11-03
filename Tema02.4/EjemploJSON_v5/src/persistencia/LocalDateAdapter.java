package persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/*
 * 	Adaptador personalizado para controlar cómo GSON convierte objetos LocalDate.
 * 	Hereda de TypeAdapter<LocalDate> para definir la lectura y escritura manual en JSON.
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
	
	// Define el formato estándar ISO-8601 (YYYY-MM-DD), p. ej. "2026-10-13"
	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ISO_LOCAL_DATE;
	
	
	@Override
    public void write(JsonWriter out, LocalDate fecha) throws IOException {
		// Convierte el objeto LocalDate a String según el formato ISO y lo escribe en el JSON
		out.value(fecha.format(FORMATO));									// objeto -> "2026-10-13"
    }
	
    @Override
    public LocalDate read(JsonReader in) throws IOException {
    	// Lee la siguiente cadena de texto en el JSON y la parsea a un objeto LocalDate
        return LocalDate.parse(in.nextString(), FORMATO);   				// "2026-10-13" -> objeto
    }

}

