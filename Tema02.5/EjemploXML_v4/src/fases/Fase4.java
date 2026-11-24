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


import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

public class Fase4 {

	public static void main(String[] args) throws Exception {
		
		List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Taladro percutor", 89.90, 12, Categoria.HERRAMIENTA,
                LocalDate.of(2026, 10, 13),
                new Proveedor("Ferrosur", "pedidos@ferrosur.example"),
                List.of("electrico", "obra")));
        
        productos.add(new Producto(2, "Polímetro digital", 24.50, 8, Categoria.ELECTRONICA,
                LocalDate.of(2026, 10, 13), null, List.of()));

        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element raiz = doc.createElement("inventario");
        doc.appendChild(raiz);

        for (Producto p : productos) {
            Element ep = doc.createElement("producto");
            ep.setAttribute("id", String.valueOf(p.getId()));
            ep.appendChild(elemento(doc, "nombre", p.getNombre()));
            ep.appendChild(elemento(doc, "precio", String.valueOf(p.getPrecio())));
            ep.appendChild(elemento(doc, "stock", String.valueOf(p.getStock())));
            ep.appendChild(elemento(doc, "categoria", p.getCategoria().name()));
            ep.appendChild(elemento(doc, "fechaAlta", p.getFechaAlta().toString()));

            if (p.getProveedor() != null) {
                Element eProveedor = doc.createElement("proveedor");
                eProveedor.appendChild(elemento(doc, "nombre", p.getProveedor().getNombre()));
                eProveedor.appendChild(elemento(doc, "email", p.getProveedor().getEmail()));
                ep.appendChild(eProveedor);
            }

            Element eEtiquetas = doc.createElement("etiquetas");
            for (String etiqueta : p.getEtiquetas()) {
                eEtiquetas.appendChild(elemento(doc, "etiqueta", etiqueta));
            }
            ep.appendChild(eEtiquetas);

            raiz.appendChild(ep);
        }

        guardar(doc, new File("inventario_salida.xml"));
        System.out.println("Guardado inventario_salida.xml");
	}
	
	/* Crea un elemento con texto: <nombre>texto</nombre>. */
    private static Element elemento(Document doc, String nombre, String texto) {
        Element e = doc.createElement(nombre);
        e.setTextContent(texto);
        return e;
    }

    /* Guarda el documento XML incluyendo la cabecera */
    private static void guardar(Document doc, File destino) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        t.transform(new DOMSource(doc), new StreamResult(destino));
    }

	
	
}
