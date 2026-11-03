package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Producto {
	
	private int id;
    private String nombre;
    private double precio;
    private int stock;
    private Categoria categoria;
    private LocalDate fechaAlta;
    private Proveedor proveedor;
    private List<String> etiquetas = new ArrayList<>();

    public Producto() { }   // lo usa GSON al deserializar

    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(int id, String nombre, double precio, int stock, Categoria categoria,
                    LocalDate fechaAlta, Proveedor proveedor, List<String> etiquetas) {
        this(id, nombre, precio, stock);
        this.categoria = categoria;
        this.fechaAlta = fechaAlta;
        this.proveedor = proveedor;
        this.etiquetas = new ArrayList<>(etiquetas);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public Categoria getCategoria() { return categoria; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public Proveedor getProveedor() { return proveedor; }
    public List<String> getEtiquetas() { return etiquetas; }

    @Override
    public String toString() {
        return String.format("#%d %-20s %8.2f € stock=%d [%s]", id, nombre, precio, stock, categoria);
    }



}
