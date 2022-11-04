package AED.UT2.PlantillasUT2.Examples;

import AED.UT2.PlantillasUT2.OracleConnector;

public class OracleConnExample {
    static OracleConnector oracleConnector;

    public static void main(String[] args) {
        oracleConnector = new OracleConnector("localhost","peng_aed","admin1234");

        System.out.println("||=====================================||Empleados Antes||=====================================||");

        String queryBefore = "select * from empleados";
        oracleConnector.executeQuery(queryBefore);

        String procedureName = "SUBIDA_SALARIO";
        String[] procedureParameters = new String[]{"1","10"};
        oracleConnector.executeProcedure(procedureName,procedureParameters);

        String insertQuery = "INSERT INTO empleados values (6,'Oracle',1000,2)";
        oracleConnector.executeUpdate(insertQuery);

        System.out.println("||=====================================||Empleados Despues||=====================================||");

        String queryAfter = "select * from empleados";
        oracleConnector.executeQuery(queryAfter);

        oracleConnector.closeConnection();
    }

}
