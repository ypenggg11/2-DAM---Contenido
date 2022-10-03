package AED.UT1.Ejemplos;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class EjemploXML {

	public static void main(String[] args) throws Exception {

		String nomdoc = "Mascotas";
		String nomelemento = "mascota";
		String campos[] = { "nombre", "chip" };

		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();

		DocumentBuilder mixml = fac.newDocumentBuilder();
		Document doc = mixml.parse(new File("./src/AED/UT1/Ejemplos/" + nomdoc + ".xml"));

		doc.getDocumentElement().normalize();

		System.out.println("Elemento raiz: "
				+ doc.getDocumentElement().getNodeName());
		NodeList elementos = doc.getElementsByTagName(nomelemento);

		for (int i = 0; i < elementos.getLength(); i++) {

			Node item = elementos.item(i);

			if (item.getNodeType() == Node.ELEMENT_NODE) {

				Element elemento = (Element) item;
				System.out.print(campos[0] + ": "
						+ leeNodo(campos[0], elemento)+"-----");
				System.out.println(campos[1] + ": "
						+ leeNodo(campos[1], elemento));
			}
		}
	}

	private static String leeNodo(String etiqueta, Element elem) {

		NodeList nodo = elem.getElementsByTagName(etiqueta).item(0)
				.getChildNodes();

		Node valor = (Node) nodo.item(0);
		return valor.getNodeValue();
	}
}
