package AED.UT1.Actividad4XML.Constants;

//Contiene únicamente constantes relacioneados con el fichero xml a leer/crear.
public class xmlConstants {

    //<Centros_educativos> -> raíz
    //<centro_educativo> -> nodo
    //<tipo><nombre>... -> campos
    //CIFP... -> valor del campo
    public static final String raiz = "Centros_educativos";
    public static final String path = "./src/AED/UT1/Actividad4XML/Outputs/"+raiz+".xml";

    public static final String nodo = "centro_educativo";
    public static final String[] campos = {"tipo","nombre","codigo","calle","codigo_postal","localidad","isla"};

    private xmlConstants(){}
}
