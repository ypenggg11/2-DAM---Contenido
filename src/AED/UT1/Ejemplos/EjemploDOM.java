package AED.UT1.Ejemplos;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class EjemploDOM {

	public static void main(String[] args) throws Exception {

		String names[] = { "Coty", "Max", "Poki" };
		Integer chip[] = { 11, 22, 33};

		// Simulamos un XDS ;-) casero
		String nomdoc = "Mascotas";
		String nomelemento = "mascota";
		String campos[] = { "nombre", "chip" };

		// Se crea una instancia de DocumentBuilderFactory para construir el
		// parser
		DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();

		// Para crear el doc. vacío con versión de XML
		DocumentBuilder mixml = fac.newDocumentBuilder();
		DOMImplementation implementa = mixml.getDOMImplementation();
		Document doc = implementa.createDocument(null, nomdoc, null);
		doc.setXmlVersion("1.0");

		for (int i = 0; i < names.length; i++) {
			// Con cada registro se crea un NODO

			Element raiz = doc.createElement(nomelemento);

			// Cada nodo tendrá hijos o elementos, los campos...
			doc.getDocumentElement().appendChild(raiz);
			CrearElemento(campos[0], names[i].trim(), raiz, doc);
			CrearElemento(campos[1], Integer.toString(chip[i]), raiz, doc);
			//Se puede optimizar ;-)
		}

		// Crea la fuente XML a partir del documento
		Source fs = new DOMSource(doc);
		Result res = new StreamResult(new File("./src/AED/UT1/Ejemplos/"+nomdoc + ".xml"));

		Transformer trans = TransformerFactory.newInstance().newTransformer();

		// Y realiza la transformación de documento a fichero
		trans.transform(fs, res);

	}

	static void CrearElemento(String dato, String valor, Element raiz,
			Document doc) {

		Element elem = doc.createElement(dato); // Se crea el elemento-hijo
		Text texto = doc.createTextNode(valor); // Se le pone su valor
		elem.appendChild(texto); // Se cuelgan los elementos-valores
		raiz.appendChild(elem);

	}
}
