package tarea2_1;

import java.math.BigDecimal;

public class Nota {

	private final int id;
	private final String nombre;
	private final String apellidos;
	private final String asignatura;
	private final int trimestre;
	// BigDecimal para que el redondeo a 2 decimales sea exacto (no como con double)
	private final BigDecimal nota;

	// El orden de los parámetros es el de las columnas del CSV
	public Nota(int id, String nombre, String apellidos, String asignatura, int trimestre, BigDecimal nota) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.asignatura = asignatura;
		this.trimestre = trimestre;
		this.nota = nota;
	}

	public int getId() { return id; }

	public String getNombre() { return nombre; }

	public String getApellidos() { return apellidos; }

	public String getAsignatura() { return asignatura; }

	public int getTrimestre() { return trimestre; }

	public BigDecimal getNota() { return nota; }

	@Override
	public String toString() {
		return "Nota [id=" + id + ", " + apellidos + ", " + nombre + ", " + asignatura
				+ ", T" + trimestre + ", " + nota + "]";
	}
}