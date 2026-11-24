package modelo;

public class Proveedor {
	
	private String nombre;
    private String email;

    public Proveedor() { }
    
    public Proveedor(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }

	@Override
	public String toString() {
		return "Proveedor [nombre=" + nombre + ", email=" + email + "]";
	}
    
}
