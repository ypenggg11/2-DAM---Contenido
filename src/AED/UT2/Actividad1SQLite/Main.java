package AED.UT2.Actividad1SQLite;

import java.sql.*;

public class Main {

    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private static final String DDBB_CONNECTION =
            "jdbc:sqlite:./src/AED/UT2/Actividad1SQLite/DevelopmentDataBasesSQLiteActividad1_BBDD.db";
    private static String[] COLUMN_DATA_TYPE;

    public static void main(String[] args) {
        try {
            //Carga el driver
            Class.forName(SQLITE_DRIVER);

            //Establece la conexión con la base de datos indicada
            Connection connection = DriverManager.getConnection(DDBB_CONNECTION);

            //Crea una sentencia
            Statement statement = connection.createStatement();
            //Ejecuta la sentencia (executeQuery para SELECT) y lo almacena en un ResultSet
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empleados");

            //Obtenemos el meta data del resultSet para acceder a su información
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //.getColumnCount() -> devuelve el número de columnas de la tabla
            COLUMN_DATA_TYPE = new String[resultSetMetaData.getColumnCount()];

            for (int i = 1;i<=COLUMN_DATA_TYPE.length;i++) {
                //.getColumnName() -> devuelve el nombre de la columna
                System.out.printf("%-20s",resultSetMetaData.getColumnName(i));
                //.getColumnTypeName() -> devuelve el nombre del tipo de dato de la columna
                COLUMN_DATA_TYPE[i-1] = resultSetMetaData.getColumnTypeName(i);
//                System.out.println(resultSetMetaData.isNullable(i));
//                System.out.println(resultSetMetaData.getColumnDisplaySize(i));
            }

            System.out.println();
            //Mientras sigan existiendo registros/filas...
            while (resultSet.next()) {
                for (int i = 1;i<=COLUMN_DATA_TYPE.length;i++) {
                    //readData() método personalizado
                    System.out.printf("%-20s",readData(resultSet,COLUMN_DATA_TYPE[i-1],i));
                }
                System.out.println();
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Dependiendo del tipo de dato, lee con su correspondiente método y devuelve el contenido leído.
    private static String readData(ResultSet resultSet,String dataType,int columPos) throws SQLException {
        String dataValue="";

        switch (dataType) {
            case "integer" ->    dataValue = String.valueOf(resultSet.getInt(columPos));
            case "text" ->    dataValue = resultSet.getString(columPos);
            case "float" ->    dataValue = String.valueOf(resultSet.getDouble(columPos));
        }

        return dataValue;
    }
}
