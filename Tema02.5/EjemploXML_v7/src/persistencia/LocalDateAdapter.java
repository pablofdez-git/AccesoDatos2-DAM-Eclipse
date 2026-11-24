package persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
	
	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ISO_LOCAL_DATE;
	
	@Override
    public void write(JsonWriter out, LocalDate fecha) throws IOException {
		out.value(fecha.format(FORMATO));					// objeto -> "2026-10-13"
    }
	
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString(), FORMATO);   // "2026-10-13" -> objeto
    }

}

