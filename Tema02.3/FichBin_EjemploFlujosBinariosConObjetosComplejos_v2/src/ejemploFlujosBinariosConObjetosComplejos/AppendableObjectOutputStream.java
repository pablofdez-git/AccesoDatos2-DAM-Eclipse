package ejemploFlujosBinariosConObjetosComplejos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendableObjectOutputStream extends ObjectOutputStream{
	
	// Constructor: recibe un flujo de salida
	public AppendableObjectOutputStream(OutputStream out) throws IOException {
        super(out);		// Llama al constructor de la superclase (ObjectOutputStream)
    }

	// Este método se llama automáticamente al crear el ObjectOutputStream. 
    // Normalmente escribe una cabecera binaria writeStreamHeader() es un método protegido
    @Override
    protected void writeStreamHeader() throws IOException {
        reset(); 		// En lugar de escribir una nueva cabecera, simplemente “reseteamos” el flujo. (Se evita escribir una nueva cabecera)
        				// Esto mantiene la compatibilidad de escritura sin corromper el archivo.
    }

}
