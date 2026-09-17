package ejercicio7;

public class Vehiculo {
	
	private String matricula;
	private boolean esElectrico;
	
	public Vehiculo(String matricula, boolean esElectrico) {
		this.matricula = matricula;
		this.esElectrico = esElectrico;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public boolean isEsElectrico() {
		return esElectrico;
	}

	public void setEsElectrico(boolean esElectrico) {
		this.esElectrico = esElectrico;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", esElectrico=" + esElectrico + "]";
	}
	

}
