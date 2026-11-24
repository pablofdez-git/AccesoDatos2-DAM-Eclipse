package modelo;

public class Articulo {
	
	private int codigo;
    private String descripcion;
    private String categoria;
    private double pvp;

    public Articulo(int codigo, String descripcion, String categoria, double pvp) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.pvp = pvp;
    }

    public int getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public double getPvp() { return pvp; }
    public void setPvp(double pvp) { this.pvp = pvp; }

    @Override
    public String toString() {
        return String.format("%d | %-25s | %-11s | %6.2f €", codigo, descripcion, categoria, pvp);
    }

}
