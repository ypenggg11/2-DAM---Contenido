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

    //Inicia todos los objetos necesarios para la escritura/lectura de un XML.
    private void initXML() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        domImplementation = documentBuilder.getDOMImplementation();

        //En nuestro caso, solo indicaremos la etiqueta del elemento raíz.
        document = domImplementation.createDocument(null, elementoRaiz, null);

        document.setXmlVersion("1.0");
    }

    //Añade un nodo/elemento hijo de la raíz, y sus respectivos campos y sus valores.
    public void addNodo(String nombreNodo, String[] elementosCampos, String[] valoresCampos) {

        //Creación del elemento hijo (de la raíz)
        Element elementoNodo = document.createElement(nombreNodo);
        //Recupera el elemento raíz del documento y le añade (append) el elemento hijo (nuesto nodo)
        document.getDocumentElement().appendChild(elementoNodo);

        for (int i = 0; i < elementosCampos.length; i++) {
            addCampo(elementosCampos[i], valoresCampos[i], elementoNodo);
        }
    }

    //Añade el valor a su respectivo campo, y luego añade dicho campo al nodo (hijo de la raíz)
    private void addCampo(String nombreCampo, String valorCampo, Element elementoNodo) {

        //Creación del campo a añadir.
        Element elementoCampo = document.createElement(nombreCampo);
        //Creación del valor de dicho campo.
        Text valor = document.createTextNode(valorCampo);

        //Añade como hijo el valor al campo
        elementoCampo.appendChild(valor);
        //Añade como hijo el campo a nuestro nodo
        elementoNodo.appendChild(elementoCampo);
    }

    //Cierre del XML, para que se guarde el contenido como fichero xml,
    //hacia la ruta que le pasemos por parámetro
    public void createXMLfile(String path) {
        //Crea la fuente del xml a partir del documento.
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(path));

        Transformer trans;

        //Transforma el documento a fichero.
        try {
            trans = TransformerFactory.newInstance().newTransformer();

            trans.transform(source, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    //Lee el fichero XML que encuentre en la ruta indicada.
    public void readXMLfile(String path,String nombreNodo,String[] campos) {
        try {
            //Convierte el fichero a documento.
            document = documentBuilder.parse(new File(path));
        } catch (SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        //Sirve para optimizar, útil para ficheros XML muy extensos.
        document.getDocumentElement().normalize();

        readNodos(nombreNodo, campos);
    }

    //Lee los nodos (elementos hijos de la raíz), del fichero XML y los muestra por consola (modificable)
    private void readNodos(String nombreNodo,String[] campos) {
        //Obtiene la lista de todos los nodos del XML.
        NodeList listaNodos = document.getElementsByTagName(nombreNodo);

        for (int i = 0; i < listaNodos.getLength(); i++) {

            //Recupera el nodo correspondiente para trabajar sobre ella. (obtener sus campos)
            Node nodo = listaNodos.item(i);

            //Verificación del tipo del nodo (si se trata de un elemento)
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {

                //Castea el nodo a elemento.
                Element elementoNodo = (Element) nodo;

                System.out.println();
                //Imprime por consola el valor se sus campos
                for (int j = 0; j < campos.length; j++) {
                    System.out.printf("%-23s", getValorCampo(campos[j], elementoNodo));
                }
            }
        }
    }

    //Devuelve el valor del campo correspondiente a un nodo en concreto.
    private String getValorCampo(String nombreCampo, Element elementoNodo) {

        //Obtiene la lista de campos dentro del nodo, cuyo nombre sea el pasado por parámetro
        //(.index(0) y .getChildNodes() no encuentro su lógica, ya que solamente se trata de
        //un único campo con ese nombre, pero es funcional de esta manera.)
        NodeList listaCampos = elementoNodo.getElementsByTagName(nombreCampo).item(0).getChildNodes();

        //Obtiene el campo en concreto a partir de la lista
        //(.item(0) es pq siempre habrá solo uno, y en la posición 0?)
        Node campo = listaCampos.item(0);

        //Devuelve el valor de ese campo
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
