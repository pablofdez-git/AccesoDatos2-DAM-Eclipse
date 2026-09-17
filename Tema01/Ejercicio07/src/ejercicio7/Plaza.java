package ejercicio7;

public class Plaza {
	
	private int num;
	private boolean ocupada;
	private boolean electricos;
	private Vehiculo vehiculo;
	
	public Plaza(int num, boolean electricos) {
		this.num = num;
		this.ocupada = false;
		this.electricos = electricos;
		this.vehiculo = null;	
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public boolean isElectricos() {
		return electricos;
	}

	public void setElectricos(boolean electricos) {
		this.electricos = electricos;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		return "Plaza numero=" + num + ", ocupada=" + ocupada + ", electricos=" + electricos + "]";
	}
		

}
