package AED.UT2.Actividad3Sentencias.ProvProd;

import java.sql.*;

public class Maim_ProvProd {
    private static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private static final String DDBB_CONNECTION =
            "jdbc:sqlite:.\\src\\AED\\UT2\\Actividad3Sentencias\\ProvProd\\prov_prod.db";
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
            //          ACTIVIDAD 2
            //===========================================================================================================================
            System.out.println("||===================================||Apartado (A)||===================================||");
            executeSelect("SELECT nombre_prod, nombre_prov, precio_prod FROM productos " +
                    "INNER JOIN proveedores ON productos.cod_prov = proveedores.cod_prov " +
                    "WHERE productos.precio_prod > 2000 ORDER BY productos.precio_prod DESC;"
            );

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (B)||===================================||");
            executeSelect("SELECT nombre_prov, telefono_prov FROM proveedores " +
                    "INNER JOIN productos ON proveedores.cod_prov = productos.cod_prov " +
                    "WHERE productos.nombre_prod IN ('ordenador');");

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (C)||===================================||");
            executeSelect("SELECT nombre_prod FROM productos WHERE stock_prod < 20;"
            );

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (D)||===================================||");
            executeSelect("SELECT cod_prod, nombre_prod, precio_prod * 0.95, stock_prod FROM productos " +
                    "INNER JOIN proveedores ON proveedores.cod_prov = productos.cod_prov " +
                    "WHERE bonifica_prov = 0;"
            );

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (E)||===================================||");
            executeSelect("SELECT nombre_prov, SUM(stock_prod), AVG(precio_prod) FROM productos " +
                    "INNER JOIN proveedores ON proveedores.cod_prov = productos.cod_prov " +
                    "GROUP BY (nombre_prov);"
            );

            //===========================================================================================================================

            System.out.println("||===================================||Apartado (F)||===================================||");
            executeSelect("SELECT nombre_prov, direccion_prov, telefono_prov, MAX(stock_prod) " +
                    "FROM proveedores INNER JOIN productos ON proveedores.cod_prov = productos.cod_prov;"
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
            for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) {
                //readData() método personalizado
                System.out.printf("%-20s", readData(resultSet, COLUMN_DATA_TYPE[i - 1], i));
            }
            System.out.println();
        }
    }

    //Printea el nombre de las columnas de la tabla.
    private static void printCols(ResultSetMetaData resultSetMetaData) throws SQLException {
        for (int i = 1; i <= COLUMN_DATA_TYPE.length; i++) {
            //.getColumnName() -> devuelve el nombre de la columna
            System.out.printf("%-20s", resultSetMetaData.getColumnName(i));
            //.getColumnTypeName() -> devuelve el nombre del tipo de dato de la columna
            COLUMN_DATA_TYPE[i - 1] = resultSetMetaData.getColumnTypeName(i);
            //System.out.println(resultSetMetaData.isNullable(i));
            //System.out.println(resultSetMetaData.getColumnDisplaySize(i));
        }
    }

    //Dependiendo del tipo de dato, lee con su correspondiente método y devuelve el contenido leído.
    private static String readData(ResultSet resultSet, String dataType, int columPos) throws SQLException {
        String dataValue = "";

        switch (dataType) {
            case "integer" -> dataValue = String.valueOf(resultSet.getInt(columPos));
            case "text" -> dataValue = resultSet.getString(columPos);
            case "float" -> dataValue = String.valueOf(resultSet.getDouble(columPos));
        }

        return dataValue;
    }
}
