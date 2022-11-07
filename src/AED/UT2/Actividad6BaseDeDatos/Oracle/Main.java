package AED.UT2.Actividad6BaseDeDatos.Oracle;

import AED.UT2.Actividad6BaseDeDatos.Util.BufferedIO;

import java.util.ArrayList;

public class Main {

    private static final String DDBB_LOCATION = "localhost";
    private static final String DDBB_USERNAME = "peng_aed";
    private static final String DDBB_PASSWORD = "admin1234";
    private static final String INSERT_SENTENCES = "./src/AED/UT2/Actividad6BaseDeDatos/SqlFiles/Insert.sql";
    private static final String SELECT_SENTENCES = "./src/AED/UT2/Actividad6BaseDeDatos/SqlFiles/Select.sql";

    public static void main(String[] args) {
        OracleConnector oracleConnector = new OracleConnector(DDBB_LOCATION,DDBB_USERNAME, DDBB_PASSWORD);

        insertToDDBB(oracleConnector);
        selectFromDDBB(oracleConnector);

        executeProcedure(oracleConnector);
        testTrigger(oracleConnector);

        oracleConnector.closeConnection();
    }

    //Comprueba el funcionamiento del trigger mediante un update y estableciendo un stock < 10.
    private static void testTrigger(OracleConnector oracleConnector) {
        oracleConnector.executeUpdate("DELETE FROM pedidos_pendientes");

        System.out.println();
        oracleConnector.executeQuery("SELECT * FROM pedidos_pendientes");

        oracleConnector.executeUpdate("UPDATE productos SET stock = stock-80 WHERE cod_prod = 4");

        System.out.println();
        oracleConnector.executeQuery("SELECT * FROM pedidos_pendientes");
    }

    //Ejecuta un procedimiento, llamado MODIF_BONIFICA, que recibe dos parámetros ->
    // -> ('cod_prov' y 'porcentaje adicional')
    private static void executeProcedure(OracleConnector oracleConnector) {
        System.out.println();
        System.out.println("||=================================||ANTES DEL PROCEDURE||=================================||");
        oracleConnector.executeQuery("SELECT * FROM proveedores");

        oracleConnector.executeProcedure("MODIF_BONIFICA",new String[]{"1","10"});

        System.out.println();
        System.out.println("||================================||DESPUES DEL PROCEDURE||================================||");
        oracleConnector.executeQuery("SELECT * FROM proveedores");
    }

    //Lee el fichero Insert.sql y ejecuta cada sentencia INSERT dentro de ella.
    private static void insertToDDBB(OracleConnector oracleConnector) {
        BufferedIO insertBufferedIO = new BufferedIO(INSERT_SENTENCES);

        ArrayList<String> insertSentences = insertBufferedIO.readFile();

        for (String sentence : insertSentences) {
            oracleConnector.executeUpdate(sentence);
        }
    }

    //Lee el fichero Select.sql y ejecuta cada consulta dentro de ella.
    private static void selectFromDDBB(OracleConnector oracleConnector) {
        BufferedIO selectBufferedIO = new BufferedIO(SELECT_SENTENCES);

        ArrayList<String> selectSentences = selectBufferedIO.readFile();

        for (int i = 0;i<selectSentences.size();i++) {
            System.out.println("||===================================||SELECT "+(i+1)+"||===================================||");
            oracleConnector.executeQuery(selectSentences.get(i));
        }
    }
}
