package AED.UT2.Actividad6BaseDeDatos.PostgreSQL;

import AED.UT2.Actividad6BaseDeDatos.Util.BufferedIO;

import java.util.ArrayList;

public class Main {

    private static final String DDBB_LOCATION = "localhost:5432";
    private static final String DDBB_NAME = "AED_ACT6";
    private static final String DDBB_USERNAME = "openpg";
    private static final String DDBB_PASSWORD = "openpgpwd";
    private static final String INSERT_SENTENCES = "./src/AED/UT2/Actividad6BaseDeDatos/SqlFiles/Insert.sql";
    private static final String SELECT_SENTENCES = "./src/AED/UT2/Actividad6BaseDeDatos/SqlFiles/Select.sql";

    public static void main(String[] args) {

        PostgreConnector postgreConnector = new PostgreConnector(DDBB_LOCATION, DDBB_NAME, DDBB_USERNAME, DDBB_PASSWORD);

        insertToDDBB(postgreConnector);

        selectFromDDBB(postgreConnector);

        postgreConnector.closeConnection();
    }

    //Lee el fichero Insert.sql y ejecuta cada sentencia INSERT dentro de ella.
    private static void insertToDDBB(PostgreConnector postgreConnector) {
        BufferedIO insertBufferedIO = new BufferedIO(INSERT_SENTENCES);

        ArrayList<String> insertSentences = insertBufferedIO.readFile();

        for (String sentence : insertSentences) {
            postgreConnector.executeUpdate(sentence);
        }
    }

    //Lee el fichero Select.sql y ejecuta cada consulta dentro de ella.
    private static void selectFromDDBB(PostgreConnector postgreConnector) {
        BufferedIO selectBufferedIO = new BufferedIO(SELECT_SENTENCES);

        ArrayList<String> selectSentences = selectBufferedIO.readFile();

        for (int i = 0;i<selectSentences.size();i++) {
            System.out.println("||===================================||SELECT "+(i+1)+"||===================================||");
            postgreConnector.executeQuery(selectSentences.get(i));
        }
    }
}
