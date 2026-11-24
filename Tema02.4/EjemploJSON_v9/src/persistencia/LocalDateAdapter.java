package persistencia;

import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
	
    @Override
    public void write(JsonWriter out, LocalDate fecha) throws IOException {
        out.value(fecha.toString());          		// objeto -> "2026-10-13"
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString());   // "2026-10-13" -> objeto
    }

}

