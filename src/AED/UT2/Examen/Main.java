package AED.UT2.Examen;

import java.util.ArrayList;

public class Main {
    private final static String FILE_PATH = "./src/AED/UT2/Examen/DML.sql";
    private static final String DDBB_LOCATION = "localhost";
    private static final String DDBB_USERNAME = "peng_aed";
    private static final String DDBB_PASSWORD = "admin1234";

    public static void main(String[] args) {
        OracleConnector oracleConnector = new OracleConnector(DDBB_LOCATION, DDBB_USERNAME, DDBB_PASSWORD);

        executeFileSentences(oracleConnector);

        executeProcedure(oracleConnector);

        oracleConnector.closeConnection();
    }

    private static void executeProcedure(OracleConnector oracleConnector) {
        System.out.println("||=======================================================||ANTES DEL PROCEDURE||=======================================================||");
        oracleConnector.executeQuery("SELECT * FROM CICLOS");
        System.out.println();

        oracleConnector.executeProcedure("MODIF_MODALIDAD",new String[]{});

        System.out.println("||===========================================================||DESPUES DEL PROCEDURE||===========================================================||");
        oracleConnector.executeQuery("SELECT * FROM CICLOS");
        System.out.println();
    }

    private static void executeFileSentences(OracleConnector oracleConnector) {
        BufferedIO insertBufferedIO = new BufferedIO(FILE_PATH);

        ArrayList<String> query = insertBufferedIO.readFile();

        for (String sentence : query) {
            if (sentence.contains("SELECT")) {
                /*
                * El apartado d.- de la actividad 4, doy a suponer que es imposible, ya que he probado todas
                * las combinaciones posibles, esto claramente en base a mis conocimientos.
                *
                * 1.- No permite añadir más campos para visualizar al utilizar MIN(COUNT(campo))
                * 2.- No se permite referenciar a los campos cuyo nombre sean utilizados con AS nombre_de_campo
                * 3.- No se permite utilizar funciones como COUNT(campo) dentro de un WHERE
                * 4.- Se permite utilizar un SELECT ANINADO, pero al no permitir los apartados 2 y 3,
                *       el dato recuperado es indiferente
                * */
                System.out.println("||============================================================||SELECT||============================================================||");
                oracleConnector.executeQuery(sentence);
                System.out.println();
            }else {
                oracleConnector.executeUpdate(sentence);
            }
        }
    }
}
