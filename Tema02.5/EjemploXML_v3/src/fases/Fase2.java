
package fases;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Fase2 {

	public static void main(String[] args) throws Exception {
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("inventario.xml"));

        Element primero = (Element) doc.getElementsByTagName("producto").item(0);
        NodeList hijos = primero.getChildNodes();
        for (int i = 0; i < hijos.getLength(); i++) {
            Node n = hijos.item(i);
            System.out.println(i + ": " + n.getNodeName() + " (tipo " + n.getNodeType() + ")");
        }
	}
}
