package AED.UT2.Actividad3Sentencias;

import java.sql.*;

public class Main {
    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private static final String DDBB_CONNECTION =
            "jdbc:sqlite:.\\src\\AED\\UT2\\Actividad3Sentencias\\emp_dpt.db";
    private static String[] COLUMN_DATA_TYPE;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void main(String[] args) {
        try {
            //Carga el driver
            Class.forName(SQLITE_DRIVER);

            //Establece la conexión con la base de datos indicada
            connection = DriverManager.getConnection(DDBB_CONNECTION);

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (A)||===================================||");
            executeSelect("SELECT nombre_departamento AS NOMBRE, localidad_departamento AS LOCALIDAD FROM departamentos;");

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (B)||===================================||");
            executeSelect("SELECT * FROM departamentos WHERE localidad_departamento NOT IN ('Madrid');");

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (C)||===================================||");
            executeSelect("SELECT empleados.* FROM empleados INNER JOIN departamentos WHERE empleados.salario > 2000 AND " +
                    "empleados.id_departamento_empleado = departamentos.id_departamento AND " +
                    "departamentos.localidad_departamento NOT IN ('Madrid');"
            );

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (D)||===================================||");
            executeSelect("SELECT empleados.* FROM empleados INNER JOIN departamentos ON " +
                    "empleados.id_departamento_empleado = departamentos.id_departamento WHERE " +
                    "((empleados.salario + empleados.comision) > 1500 AND " +
                    "departamentos.nombre_departamento NOT IN ('Administracion')) OR " +
                    "(empleados.comision = 0 AND " +
                    "departamentos.nombre_departamento = 'Administracion');"
            );

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Ejecuta una sentencia SELECT y printea por consola su contenido
    private static void executeSelect(String sentence) throws SQLException {
        //Crea una sentencia
        statement = connection.createStatement();

        //Ejecuta la sentencia (executeQuery para SELECT) y lo almacena en un ResultSet
        resultSet = statement.executeQuery(sentence);

        //Obtenemos el meta data del resultSet para acceder a su información
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        //.getColumnCount() -> devuelve el número de columnas de la tabla
        COLUMN_DATA_TYPE = new String[resultSetMetaData.getColumnCount()];

        printSelect(resultSetMetaData);
    }

    //Llama a los métodos para imprimir el nombre de las columnas y los registros de un SELECT
    private static void printSelect(ResultSetMetaData resultSetMetaData) throws SQLException {
        printCols(resultSetMetaData);
        System.out.println();
        printDataContent(resultSet);
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
            case "integer" ->    dataValue = String.valueOf(resultSet.getInt(columPos));
            case "text" ->    dataValue = resultSet.getString(columPos);
            case "float" ->    dataValue = String.valueOf(resultSet.getDouble(columPos));
        }

        return dataValue;
    }
}
