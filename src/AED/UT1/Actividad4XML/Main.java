package AED.UT1.Actividad4XML;

import AED.UT1.Actividad4XML.Constants.xmlConstants;
import AED.UT1.Actividad4XML.IOControls.ObjectIO;
import AED.UT1.Actividad4XML.IOControls.xmlIO;
import AED.UT1.Actividad4XML.Objects.CentroEducativo;

import java.util.ArrayList;

public class Main {

    //<Centros_educativos> -> raíz
    //<centro_educativo> -> nodo
    //<tipo><nombre>... -> campos
    //CIFP... -> valor del campo
    private static String path = "./src/AED/UT1/Actividad4XML/Outputs/centros_educativos.obj";
    private static ArrayList<CentroEducativo> centrosEducativos;
    //Clase personalizada que contiene métodos para leer y escribir objetos.
    private static ObjectIO objectIO;
    //Clase personalizada que contiene métodos para leer y escribir ficheros XML.
    private static xmlIO xmlIO;

    public static void main(String[] args) {
        initCentrosEducativos();

        objectIO = new ObjectIO(path);
        objectIO.writeObject(centrosEducativos);

        //xmlConstants es una clase personalizada que contiene puramente constantes
        //relacionados con el fichero xml.
        xmlIO = new xmlIO(xmlConstants.raiz);

        writeXMLFile();
        xmlIO.createXMLfile(xmlConstants.path);

        printTitulo();
        xmlIO.readXMLfile(xmlConstants.path,xmlConstants.nodo,xmlConstants.campos);
    }

    //Escribe los elementos de nuestra lista al fichero XML.
    private static void writeXMLFile() {
        for (CentroEducativo centro: objectIO.readObjectToList()) {
            String[] valorCampos = {
                    centro.getTipo(),centro.getNombre(),centro.getCodigo(),
                    centro.getCalle(),centro.getCodigoPostal(),centro.getLocalidad(),centro.getIsla()
            };

            xmlIO.addNodo(xmlConstants.nodo, xmlConstants.campos, valorCampos);
        }
    }

    //Muestra por consola el título (elemento raíz) y los campos del fichero xml.
    private static void printTitulo() {
        System.out.println(
                "||=================================================================||"
                        + xmlIO.getDocument().getDocumentElement().getNodeName() +
                        "||=================================================================||"
        );

        for (int x = 0; x < xmlConstants.campos.length; x++) {
            System.out.printf("%-23s", xmlConstants.campos[x].toUpperCase());
        }
        System.out.println();
    }

    //Crea los los objetos CentroEducativo, y los añade a la lista.
    private static void initCentrosEducativos() {
        centrosEducativos = new ArrayList<>();

        centrosEducativos.add(new CentroEducativo(
                "CIFP","VILLA DE AGUIMES","35014664","ALCORAC",
                "35118","AGUIMES","GRAN CANARIA"
        ));
        centrosEducativos.add(new CentroEducativo(
                "CIFP","GUMERSINDO MARTELL","35006734","REY ABAN",
                "35637","VALLE DE SANTA INES","FUERTEVENTURA"
        ));
        centrosEducativos.add(new CentroEducativo(
                "IES","GUAZA","38015187","GENERAL EL VALLE",
                "38627","GUAZA","TENERIFE"
        ));
        centrosEducativos.add(new CentroEducativo(
                "CEIP","FRONTON","35006783",
                "CAMINO FRONTON","35422","FRONTON","GRAN CANARIA"
        ));
        centrosEducativos.add(new CentroEducativo(
                "IES","YAIZA","35009218",
                "MACHER","35571","YAIZA","LANZAROTE"
        ));
    }
}
