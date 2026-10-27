package ejercicio2_24;

public class Empleado {
	
	private int codigo;
	private String nombre;
	private float salario;
	private double comision;
	private char departamento;
	private boolean titulacion;
	
	public Empleado(int codigo, String nombre, float salario, double comision, char departamento, boolean titulacion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.salario = salario;
		this.comision = comision;
		this.departamento = departamento;
		this.titulacion = titulacion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public char getDepartamento() {
		return departamento;
	}

	public void setDepartamento(char departamento) {
		this.departamento = departamento;
	}

	public boolean isTitulacion() {
		return titulacion;
	}

	public void setTitulacion(boolean titulacion) {
		this.titulacion = titulacion;
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", nombre=" + nombre + ", salario=" + salario + ", comision=" + comision
				+ ", departamento=" + departamento + ", titulacion=" + titulacion + "]";
	}
	
}
