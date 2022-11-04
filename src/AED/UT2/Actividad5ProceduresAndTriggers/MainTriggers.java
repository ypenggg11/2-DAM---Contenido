package AED.UT2.Actividad5ProceduresAndTriggers;

import java.sql.*;

public class MainTriggers {

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
            ResultSet resultSet = statement.executeQuery("select * from empleados");

            //Obtenemos el meta data del resultSet para acceder a su información
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            //.getColumnCount() -> devuelve el número de columnas de la tabla
            COLUMN_DATA_TYPE = new String[resultSetMetaData.getColumnCount()];

            System.out.println("||=====================================||Empleados Antes||=====================================||");

            printCols(resultSetMetaData);

            System.out.println();

            printDataContent(resultSet);

            ResultSet resultSet2 = statement.executeQuery("select * from LOG");
            ResultSetMetaData resultSetMetaData2 = resultSet2.getMetaData();
            COLUMN_DATA_TYPE = new String[resultSetMetaData2.getColumnCount()];

            System.out.println("||=====================================||LOG||=====================================||");

            printCols(resultSetMetaData2);

            System.out.println();

            printDataContent(resultSet2);

            //Ejecuta sentencias como Update/Delete/Insert
            String sql = "DELETE FROM empleados WHERE id_emp = 7";
            statement.executeUpdate(sql);

            ResultSet resultSet3 = statement.executeQuery("select * from empleados");
            ResultSetMetaData resultSetMetaData3 = resultSet3.getMetaData();
            COLUMN_DATA_TYPE = new String[resultSetMetaData3.getColumnCount()];

            System.out.println("||=====================================||Empleados Despues||=====================================||");

            printCols(resultSetMetaData3);

            System.out.println();

            printDataContent(resultSet3);

            ResultSet resultSet4 = statement.executeQuery("select * from LOG");
            ResultSetMetaData resultSetMetaData4 = resultSet4.getMetaData();
            COLUMN_DATA_TYPE = new String[resultSetMetaData4.getColumnCount()];

            System.out.println("||=====================================||LOG||=====================================||");

            printCols(resultSetMetaData4);

            System.out.println();

            printDataContent(resultSet4);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Ejecuta un procedimiento con el nombre y los valores su parámetro que le pasemos.
    private static void executeProcedure(Connection connection,String procedureName,String[] paramData) throws SQLException {

        int paramAmount = paramData.length;

        //Preparamos el procedimiento.
        CallableStatement callableStatement = connection.prepareCall(getProcedureQuery(procedureName,paramAmount));

        //Le pasamos los parámetros del procedimiento. (Empieza en 1)
        for (int i = 1;i<=paramAmount;i++) {
            callableStatement.setString(i, paramData[i-1]);
        }

        //Ejecuta el procedimiento.
        callableStatement.execute();
    }

    //Obtenemos la sentencia del procedimiento en base a su nombre y el número de parámetros.
    private static String getProcedureQuery(String procedureName, int paramAmount) {
        //La estructura es: {call NOMBRE_PROCEDIMIENTO(?,?)}, el número de '?'
        //dependerá del número de parámetros.
        StringBuilder query = new StringBuilder("{call "+ procedureName +"()}");

        //Nos situamos justo en la apertura del paréntesis.
        int pos = query.indexOf("(")+1;

        //Añadimos un '?' por cada parámetro.
        for (int i = 0; i< paramAmount; i++){
            if (!(i== paramAmount -1)) {
                query.insert(pos,"?,");
                pos+=2;
            }else {
                query.insert(pos,"?");
            }
        }

        return query.toString();
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
