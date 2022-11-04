package AED.UT2.PlantillasUT2.Examples;

import AED.UT2.PlantillasUT2.SQLiteConnector;

public class SQLiteConnExample {
    public static void main(String[] args) {
        SQLiteConnector sqLiteConnector = new SQLiteConnector("./src/AED/UT2/Actividad1SQLite/DevelopmentDataBasesSQLiteActividad1_BBDD.db");

        sqLiteConnector.executeQuery("select * from empleados");

        sqLiteConnector.executeUpdate("INSERT INTO empleados values (4,'Test','Prueba','2022-11-04',1200,0,0)");

        System.out.println();
        sqLiteConnector.executeQuery("select * from empleados");

        sqLiteConnector.closeConnection();
    }
}
