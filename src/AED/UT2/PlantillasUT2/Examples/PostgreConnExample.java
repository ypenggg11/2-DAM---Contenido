package AED.UT2.PlantillasUT2.Examples;

import AED.UT2.PlantillasUT2.PostgreConnector;

public class PostgreConnExample {
    public static void main(String[] args) {
        PostgreConnector postgreConnector = new PostgreConnector("localhost:5432","openpg","openpgpwd");

        postgreConnector.executeQuery("select * from departamentos");

        postgreConnector.executeUpdate("INSERT INTO departamentos values (3,'Oracle','Las Palmas')");

        System.out.println();
        postgreConnector.executeQuery("select * from departamentos");

        postgreConnector.closeConnection();
    }
}
