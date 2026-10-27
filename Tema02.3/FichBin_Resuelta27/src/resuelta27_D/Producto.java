package resuelta27_D;

public class Producto {
	
	private boolean activo; // Nuevo atributo para borrado lógico
	private int id;
    private String nombre;
    private double precio;
    private boolean descuento;
    private char tipo;
 
    // Construtor completo (al leer del fichero)
    public Producto(boolean activo, int id, String nombre, double precio, boolean descuento, char tipo) {
        this.activo = activo;
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descuento = descuento;
        this.tipo = tipo;
    }
    
    // Constructor para crear productos nuevos (por defecto nacen activos)
    public Producto(int id, String nombre, double precio, boolean descuento, char tipo) {
        this(true, id, nombre, precio, descuento, tipo);
    }
 
    public boolean isActivo() {
        return activo;
    }
 
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public double getPrecio() {
        return precio;
    }
 
    public void setPrecio(double precio) {
        this.precio = precio;
    }
 
    public boolean isDescuento() {
        return descuento;
    }
 
    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }
 
    public char getTipo() {
        return tipo;
    }
 
    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
 
    @Override
    public String toString() {
        return "Producto{" + "activo=" + activo + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", descuento=" + descuento + ", tipo=" + tipo + '}';
    }

}
