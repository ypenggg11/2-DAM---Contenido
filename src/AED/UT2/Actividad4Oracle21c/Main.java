package AED.UT2.Actividad4Oracle21c;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class Main {

    private static final String SQLITE_DRIVER = "oracle.jdbc.driver.OracleDriver";

    private static final String DDBB_CONNECTION =
            "jdbc:oracle:thin:@localhost/XE";

//    Mediante IP en la misma red.
//    private static final String DDBB_CONNECTION =
//            "jdbc:oracle:thin:@192.168.192.75:1521/XE";
    private static final String DDBB_USERNAME = "peng_aed";
    private static final String DDBB_PASSWD = "admin1234";
    private static String[] COLUMN_DATA_TYPE;

    public static void main(String[] args) {
        try {
            //Carga el driver
            Class.forName(SQLITE_DRIVER);

            //Establece la conexión con la base de datos indicada
            Connection connection = DriverManager.getConnection(DDBB_CONNECTION,DDBB_USERNAME,DDBB_PASSWD);

            //Crea una sentencia
            Statement statement = connection.createStatement();
            //Ejecuta la sentencia (executeQuery para SELECT) y lo almacena en un ResultSet
            ResultSet resultSet = statement.executeQuery("select * from departamentos");

            //Obtenemos el meta data del resultSet para acceder a su información
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //.getColumnCount() -> devuelve el número de columnas de la tabla
            COLUMN_DATA_TYPE = new String[resultSetMetaData.getColumnCount()];

            System.out.println("||=====================================||Departamentos||=====================================||");

            printCols(resultSetMetaData);

            System.out.println();

            printDataContent(resultSet);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Printea todos los datos de una tabla.
    private static void printDataContent(ResultSet resultSet) throws SQLException {
        //Mientras sigan existiendo registros/filas...
        while (resultSet.next()) {
            for (int i = 1;i<=COLUMN_DATA_TYPE.length;i++) {
                //readData() método personalizado
                System.out.printf("%-20s",readData(resultSet,COLUMN_DATA_TYPE[i-1],i));
            }
            System.out.println();
        }
    }

    //Printea el nombre de las columnas de la tabla.
    private static void printCols(ResultSetMetaData resultSetMetaData) throws SQLException {
        for (int i = 1;i<=COLUMN_DATA_TYPE.length;i++) {
            //.getColumnName() -> devuelve el nombre de la columna
            System.out.printf("%-20s", resultSetMetaData.getColumnName(i));
            //.getColumnTypeName() -> devuelve el nombre del tipo de dato de la columna
            COLUMN_DATA_TYPE[i-1] = resultSetMetaData.getColumnTypeName(i);
            //System.out.println(resultSetMetaData.isNullable(i));
            //System.out.println(resultSetMetaData.getColumnDisplaySize(i));
        }
    }

    //Dependiendo del tipo de dato, lee con su correspondiente método y devuelve el contenido leído.
    private static String readData(ResultSet resultSet,String dataType,int columPos) throws SQLException {
        String dataValue="";
        switch (dataType) {
            case "NUMBER" ->    dataValue = String.valueOf(resultSet.getInt(columPos));
            case "VARCHAR2" ->    dataValue = resultSet.getString(columPos);
        }

        return dataValue;
    }
}
