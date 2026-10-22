package ejercicio2_23;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	String id;
	String nombre;
	String telefono;
	
	public Cliente(String id, String nombre, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente: id->" + id + ", nombre->" + nombre + ", telefono->" + telefono;
	}
	
	
	

}
