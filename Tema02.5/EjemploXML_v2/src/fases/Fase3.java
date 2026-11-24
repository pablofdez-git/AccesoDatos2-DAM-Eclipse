
package fases;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

public class Fase3 {

	public static void main(String[] args) throws Exception {
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("inventario.xml"));

        List<Producto> productos = new ArrayList<>();
        NodeList nodos = doc.getElementsByTagName("producto");

        for (int i = 0; i < nodos.getLength(); i++) {
            Element e = (Element) nodos.item(i);

            int id = Integer.parseInt(e.getAttribute("id"));
            String nombre = textoHijo(e, "nombre");
            double precio = Double.parseDouble(textoHijo(e, "precio"));
            int stock = Integer.parseInt(textoHijo(e, "stock"));
            Categoria categoria = Categoria.valueOf(textoHijo(e, "categoria"));

            String textoFecha = textoHijo(e, "fechaAlta");
            LocalDate fecha = textoFecha == null ? null : LocalDate.parse(textoFecha);

            Proveedor proveedor = null;
            Element eProveedor = hijo(e, "proveedor");
            if (eProveedor != null) {
                proveedor = new Proveedor(textoHijo(eProveedor, "nombre"), textoHijo(eProveedor, "email"));
            }

            List<String> etiquetas = new ArrayList<>();
            Element eEtiquetas = hijo(e, "etiquetas");
            if (eEtiquetas != null) {
                NodeList lista = eEtiquetas.getElementsByTagName("etiqueta");
                for (int j = 0; j < lista.getLength(); j++) {
                    etiquetas.add(lista.item(j).getTextContent().trim());
                }
            }

            productos.add(new Producto(id, nombre, precio, stock, categoria, fecha, proveedor, etiquetas));
        }

        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    /** Devuelve el hijo directo con ese nombre, o null si no existe. */
    private static Element hijo(Element padre, String nombre) {
        NodeList hijos = padre.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);
            if (n instanceof Element && n.getNodeName().equals(nombre)) {
                return (Element) n;
            }
        }
        return null;
    }

    /** Devuelve el texto del hijo directo con ese nombre, o null si no existe. */
    private static String textoHijo(Element padre, String nombre) {
        Element e = hijo(padre, nombre);
        return e == null ? null : e.getTextContent().trim();
    }

}
