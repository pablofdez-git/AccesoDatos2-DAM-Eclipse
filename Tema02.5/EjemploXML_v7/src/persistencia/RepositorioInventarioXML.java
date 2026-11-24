package persistencia;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modelo.Categoria;
import modelo.Producto;
import modelo.Proveedor;

public class RepositorioInventarioXML {
	
	private final Path fichero;

    public RepositorioInventarioXML(Path fichero) {
        this.fichero = fichero;
    }

    public List<Producto> cargar() throws IOException {
        if (Files.notExists(fichero)) {
            return new ArrayList<>();
        }
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(fichero.toFile());

            List<Producto> productos = new ArrayList<>();
            NodeList nodos = doc.getElementsByTagName("producto");
            for (int i = 0; i < nodos.getLength(); i++) {
                productos.add(leerProducto((Element) nodos.item(i)));
            }
            return productos;
        } catch (SAXException | ParserConfigurationException | IllegalArgumentException | DateTimeParseException e) {
            throw new IOException("Inventario XML corrupto: " + fichero, e);
        }
    }

    public void guardar(List<Producto> productos) throws IOException {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element raiz = doc.createElement("inventario");
            doc.appendChild(raiz);
            for (Producto p : productos) {
                raiz.appendChild(crearElemento(doc, p));
            }

            Files.createDirectories(fichero.toAbsolutePath().getParent());
            Path tmp = fichero.resolveSibling(fichero.getFileName() + ".tmp");
            escribir(doc, tmp);
            Files.move(tmp, fichero, StandardCopyOption.REPLACE_EXISTING);
        } catch (ParserConfigurationException | TransformerException e) {
            throw new IOException("No se pudo guardar el inventario XML: " + fichero, e);
        }
    }
    
    
    // ---------------------------------------------------------------
    // XML -> objeto
    // ---------------------------------------------------------------

    private Producto leerProducto(Element e) {
        int id = Integer.parseInt(e.getAttribute("id"));
        String nombre = textoObligatorio(e, "nombre", id);
        double precio = Double.parseDouble(textoObligatorio(e, "precio", id));
        int stock = Integer.parseInt(textoObligatorio(e, "stock", id));
        Categoria categoria = Categoria.valueOf(textoObligatorio(e, "categoria", id));

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

        return new Producto(id, nombre, precio, stock, categoria, fecha, proveedor, etiquetas);
    }

    private Element hijo(Element padre, String nombre) {
        NodeList hijos = padre.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);
            if (n instanceof Element && n.getNodeName().equals(nombre)) {
                return (Element) n;
            }
        }
        return null;
    }

    private String textoHijo(Element padre, String nombre) {
        Element e = hijo(padre, nombre);
        return e == null ? null : e.getTextContent().trim();
    }

    private String textoObligatorio(Element padre, String nombre, int idProducto) {
        String texto = textoHijo(padre, nombre);
        if (texto == null) {
            throw new IllegalArgumentException(
                    "Falta <" + nombre + "> en el producto " + idProducto);
        }
        return texto;
    }
    
    
    // ---------------------------------------------------------------
    // objeto -> XML
    // ---------------------------------------------------------------

    private Element crearElemento(Document doc, Producto p) {
        Element ep = doc.createElement("producto");
        ep.setAttribute("id", String.valueOf(p.getId()));
        ep.appendChild(elemento(doc, "nombre", p.getNombre()));
        ep.appendChild(elemento(doc, "precio", String.valueOf(p.getPrecio())));
        ep.appendChild(elemento(doc, "stock", String.valueOf(p.getStock())));
        ep.appendChild(elemento(doc, "categoria", p.getCategoria().name()));
        
        if (p.getFechaAlta() != null) {
            ep.appendChild(elemento(doc, "fechaAlta", p.getFechaAlta().toString()));
        }

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
        return ep;
    }

    private Element elemento(Document doc, String nombre, String texto) {
        Element e = doc.createElement(nombre);
        e.setTextContent(texto);
        return e;
    }

    private void escribir(Document doc, Path destino) throws TransformerException {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        t.transform(new DOMSource(doc), new StreamResult(destino.toFile()));
    }

}
