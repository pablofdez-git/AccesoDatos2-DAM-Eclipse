package tarea2_1;

/**
 * Una fila del CSV que no ha pasado la validación.
 * Guarda el número de línea, el motivo y el contenido original sin modificar,
 * que es lo que se escribe después en descartadas.txt.
 */
public class FilaDescartada {
	
	private final int numeroLinea;          // la cabecera es la línea 1
	private final MotivoDescarte motivo;
	private final String contenido;         // la línea tal cual, sin trim ni cambios

	public FilaDescartada(int numeroLinea, MotivoDescarte motivo, String contenido) {
		this.numeroLinea = numeroLinea;
		this.motivo = motivo;
		this.contenido = contenido;
	}

	public int getNumeroLinea() { return numeroLinea; }

	public MotivoDescarte getMotivo() { return motivo; }

	public String getContenido() { return contenido; }

	@Override
	public String toString() {
		return "Linea " + numeroLinea + " | " + motivo + " | " + contenido;
	}
}