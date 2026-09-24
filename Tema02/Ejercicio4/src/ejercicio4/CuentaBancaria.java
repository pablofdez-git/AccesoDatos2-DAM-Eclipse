package ejercicio4;

public class CuentaBancaria {
	
	double saldoIncial;

	public CuentaBancaria(double saldoIncial) {
		this.saldoIncial = saldoIncial;
	}

	public double getSaldoIncial() {
		return saldoIncial;
	}

	public void setSaldoIncial(double saldoIncial) {
		this.saldoIncial = saldoIncial;
	}
	
	public double ingresar(double cantidad) {
		double ingreso = this.saldoIncial + cantidad;
		return ingreso;
	}
	
	public double retirar(double cantidad) {
		double retiro = this.saldoIncial - cantidad;
		return retiro;
	}
	
	

}
