package resuelta26;

public class Alumno {
	
	private String nombre;
	private int edad;
	private String cicloMatriculado;
	private double notaMedia;
	
	public Alumno(String nombre, int edad, String cicloMatriculado, double notaMedia) {
		this.nombre = nombre;
		this.edad = edad;
		this.cicloMatriculado = cicloMatriculado;
		this.notaMedia = notaMedia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getCicloMatriculado() {
		return cicloMatriculado;
	}

	public void setCicloMatriculado(String cicloMatriculado) {
		this.cicloMatriculado = cicloMatriculado;
	}

	public double getNotaMedia() {
		return notaMedia;
	}

	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}
	
	public String toCSV() {
        return nombre + "," + edad + "," + cicloMatriculado + "," + notaMedia;
    }

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", edad=" + edad + ", cicloMatriculado=" + cicloMatriculado + ", notaMedia=" + notaMedia + "]";
	}


}
