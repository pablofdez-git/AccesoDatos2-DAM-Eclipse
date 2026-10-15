package ejercicio14;

public class Restaurante {
	
	String nombre;
	String ubicacion;
	String tipoComida;
	double puntuacion;
	String telefono;
	String ciudad;
	
	public Restaurante(String nombre, String ubicacion, String tipoComida, double puntuacion, String telefono,
			String ciudad) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.tipoComida = tipoComida;
		this.puntuacion = puntuacion;
		this.telefono = telefono;
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipoComida() {
		return tipoComida;
	}

	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Restaurante [nombre=" + nombre + ", ubicacion=" + ubicacion + ", tipoComida=" + tipoComida
				+ ", puntuacion=" + puntuacion + ", telefono=" + telefono + ", ciudad=" + ciudad + "]";
	}
	
}
