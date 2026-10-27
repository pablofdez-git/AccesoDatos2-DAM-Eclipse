package ejercicio2_25;

public class Cuenta {
	
	private int numCuenta;
	private String titular;
	private double saldo;
	private char tipo;
	
	public Cuenta(int numCuenta, String titular, double saldo, char tipo) {
		this.numCuenta = numCuenta;
		this.titular = titular;
		this.saldo = saldo;
		this.tipo = tipo;
	}

	public int getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(int numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "NumCuenta=" + numCuenta + ", Titular=" + titular + ", Saldo=" + saldo + ", Tipo=" + tipo;
	}
	
	
	

}
