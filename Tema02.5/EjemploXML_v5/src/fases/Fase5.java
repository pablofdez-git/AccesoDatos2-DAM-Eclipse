package fases;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

public class Fase5 {

	public static void main(String[] args) throws Exception {
		
		File fichero = new File("inventario.xml");
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
        NodeList productos = doc.getElementsByTagName("producto");

        // 1) Consulta: nombres de los productos con menos de 20 unidades
        System.out.println("A reponer:");
        for (int i = 0; i < productos.getLength(); i++) {
            Element p = (Element) productos.item(i);
            int stock = Integer.parseInt(textoHijo(p, "stock"));
            if (stock < 20) {
                System.out.println("  " + textoHijo(p, "nombre"));
            }
        }

        // 2) Modificación: vender una unidad del producto con el id indicado
        int idBuscado = 1;
        Element encontrado = null;
        for (int i = 0; i < productos.getLength(); i++) {
            Element p = (Element) productos.item(i);
            if (Integer.parseInt(p.getAttribute("id")) == idBuscado) {
                encontrado = p;
                break;
            }
        }
        if (encontrado == null) {
            System.out.println("No existe el producto " + idBuscado);
            return;
        }

        Element eStock = hijo(encontrado, "stock");
        int nuevo = Integer.parseInt(eStock.getTextContent().trim()) - 1;
        eStock.setTextContent(String.valueOf(nuevo));
        System.out.println("\nStock del producto " + idBuscado + ": " + nuevo);

        guardar(doc, fichero);
		
    }

	/* Devuelve el hijo directo con ese nombre, o null si no existe. */
    private static Element hijo(Element padre, String nombre) {
        NodeList hijos = padre.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals(nombre)) {
                return (Element) n;
            }
        }
        return null;
    }

    /* Devuelve el texto del hijo directo con ese nombre, o null si no existe. */
    private static String textoHijo(Element padre, String nombre) {
        Element e = hijo(padre, nombre);
        return e == null ? null : e.getTextContent().trim();
    }

    private static void guardar(Document doc, File destino) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.transform(new DOMSource(doc), new StreamResult(destino));
    }

	
}
