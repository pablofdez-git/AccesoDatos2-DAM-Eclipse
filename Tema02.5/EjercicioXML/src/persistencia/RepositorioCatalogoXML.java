package persistencia;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import modelo.Articulo;

public class RepositorioCatalogoXML {
	
	private String proveedor;

    public String getProveedor() {
        return proveedor;
    }

    /**
     * Lee el fichero XML.
     *  - Guarda en el atributo "proveedor" el valor del atributo proveedor de <catalogo>.
     *  - Devuelve una lista con un Articulo por cada <articulo> del fichero.
     */
    public List<Articulo> cargar(File fichero) throws Exception {
    	Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fichero);
    	
    	Element raiz = doc.getDocumentElement();
    	proveedor = raiz.getAttribute("proveedor");
    	
    	List<Articulo> articulos = new ArrayList<>();
    	NodeList lista = doc.getElementsByTagName("articulo");
    	
    	for(int i  = 0; i<lista.getLength();i++) {
    		Element ea = (Element) lista.item(i);
    		
    		int codigo = Integer.parseInt(ea.getAttribute("codigo"));
            String categoria = ea.getAttribute("categoria");
            
            String descripcion = ea.getElementsByTagName("descripcion").item(0).getTextContent();
            double pvp = Double.parseDouble(ea.getElementsByTagName("pvp").item(0).getTextContent());

            articulos.add(new Articulo(codigo, descripcion, categoria, pvp));
    	}
    	
        return articulos;		// El return debe modificarse!!
    }

    /**
     * Escribe el catálogo en el fichero XML, con sangría y en UTF-8.
     * Debe tener el mismo formato que catalogo.xml: raíz <catalogo proveedor="...">
     * y, dentro, un <articulo codigo="..." categoria="..."> por cada artículo
     * con sus elementos <descripcion> y <qvp>.
     */
    public void guardar(List<Articulo> articulos, File fichero) throws Exception {
    	Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    	
    	Element raiz = doc.createElement("catalogo");
        raiz.setAttribute("proveedor", proveedor);
        doc.appendChild(raiz);
        
        for (Articulo a : articulos) {
            Element ea = doc.createElement("articulo");
            ea.setAttribute("codigo", String.valueOf(a.getCodigo()));
            ea.setAttribute("categoria", a.getCategoria());

            ea.appendChild(elemento(doc, "descripcion", a.getDescripcion()));
            ea.appendChild(elemento(doc, "pvp", String.format(Locale.US, "%.2f", a.getPvp())));

            raiz.appendChild(ea);
            
           
        }
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.transform(new DOMSource(doc), new StreamResult(fichero));
    }
    
    private Element elemento(Document doc, String nombre, String texto) {
        Element e = doc.createElement(nombre);
        e.setTextContent(texto);
        return e;
    }

}
