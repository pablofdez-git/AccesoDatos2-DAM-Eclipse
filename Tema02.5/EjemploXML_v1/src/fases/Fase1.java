package fases;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Fase1 {

	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        Document doc = constructor.parse(new File("inventario.xml"));

        Element raiz = doc.getDocumentElement();
        System.out.println("Elemento raíz: " + raiz.getTagName());
        System.out.println("Productos: " + raiz.getElementsByTagName("producto").getLength());

        Element primero = (Element) raiz.getElementsByTagName("producto").item(0);
        System.out.println("Id del primero: " + primero.getAttribute("id"));
        System.out.println("Nombre: " + primero.getElementsByTagName("nombre").item(0).getTextContent());


	}

}
