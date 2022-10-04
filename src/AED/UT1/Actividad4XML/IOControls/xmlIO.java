package AED.UT1.Actividad4XML.IOControls;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class xmlIO {

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private DOMImplementation domImplementation;
    private Document document;
    private String elementoRaiz;

    public xmlIO(String nombreRaiz) {
        this.elementoRaiz = nombreRaiz;

        initXML();
    }

    private void initXML() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        domImplementation = documentBuilder.getDOMImplementation();

        document = domImplementation.createDocument(null, elementoRaiz, null);

        document.setXmlVersion("1.0");
    }

    public void addNodo(String nombreNodo, String[] elementosCampos, String[] valoresCampos) {

        Element elementoNodo = document.createElement(nombreNodo);
        document.getDocumentElement().appendChild(elementoNodo);

        for (int i = 0; i < elementosCampos.length; i++) {
            addCampo(elementosCampos[i], valoresCampos[i], elementoNodo);
        }
    }

    private void addCampo(String nombreCampo, String valorCampo, Element elementoNodo) {

        Element elementoCampo = document.createElement(nombreCampo);
        Text valor = document.createTextNode(valorCampo);

        elementoCampo.appendChild(valor);
        elementoNodo.appendChild(elementoCampo);
    }

    public void createXMLfile(String path) {
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(path));

        Transformer trans;

        try {
            trans = TransformerFactory.newInstance().newTransformer();

            trans.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public void readXMLfile(String path,String nombreNodo,String[] campos) {
        try {
            document = documentBuilder.parse(new File(path));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        document.getDocumentElement().normalize();

        readNodos(nombreNodo, campos);
    }

    private void readNodos(String nombreNodo,String[] campos) {
        NodeList listaNodos = document.getElementsByTagName(nombreNodo);

        for (int i = 0; i < listaNodos.getLength(); i++) {

            Node nodo = listaNodos.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                Element elementoNodo = (Element) nodo;

                System.out.println();
                for (int j = 0; j < campos.length; j++) {
                    System.out.printf("%-23s", getValorCampo(campos[j], elementoNodo));
                }
            }
        }
    }

    private String getValorCampo(String nombreCampo, Element elementoNodo) {

        NodeList listaCampos = elementoNodo.getElementsByTagName(nombreCampo).item(0).getChildNodes();

        Node campo = listaCampos.item(0);
        return campo.getNodeValue();
    }

    public DocumentBuilderFactory getDocumentBuilderFactory() {
        return documentBuilderFactory;
    }

    public void setDocumentBuilderFactory(DocumentBuilderFactory documentBuilderFactory) {
        this.documentBuilderFactory = documentBuilderFactory;
    }

    public DocumentBuilder getDocumentBuilder() {
        return documentBuilder;
    }

    public void setDocumentBuilder(DocumentBuilder documentBuilder) {
        this.documentBuilder = documentBuilder;
    }

    public DOMImplementation getDomImplementation() {
        return domImplementation;
    }

    public void setDomImplementation(DOMImplementation domImplementation) {
        this.domImplementation = domImplementation;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getElementoRaiz() {
        return elementoRaiz;
    }

    public void setElementoRaiz(String elementoRaiz) {
        this.elementoRaiz = elementoRaiz;
    }
}
