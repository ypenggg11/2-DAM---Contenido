package PGV.Tests.UT1_Practica2;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        filterTaskList();

        dateInputAndOutput();
    }

    //Ejemplo de salida y entrada de datos desde cmd a la consola de java
    private static void dateInputAndOutput() {
        Process process;

        //Lectura de una salida que devuelve un proceso
        InputStream inputStream;
        BufferedWriter bufferedWriter;

        //Salida de datos desde java, que actuará como entrada para otro proceso (p.ej: cmd)
        OutputStream outputStream;
        BufferedReader bufferedReader;

        try {
            //Ejecuta el comando date en el cmd
            process = new ProcessBuilder("cmd", "/C", "date").start();


            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            outputStream = process.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String linea;

            //Tienes que ponerlo encima ya que la velocidad de lectura es demasiado rápido
            String newDate = "15-11-2003";

            bufferedWriter.write(newDate+"\n");
            bufferedWriter.flush();

            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println("\n"+linea);
            }

            bufferedWriter.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Filtra el resultado de tasklist (comando en cmd), y devuelve aquellos
    //que correspondan con el nombre que indiquemos
    private static void filterTaskList() {
        Process process;

        //Lectura de una salida que devuelve un proceso
        InputStream inputStream;

        //Extensión para InputStream (opcional, pero facilita la lectura/escritura)
        BufferedReader bufferedReader;

        try {
            //Ejecuta en el cmd, el comando tasklist
            process = new ProcessBuilder("cmd", "/C", "tasklist").start();

            //Obtiene la salida de datos del proceso y lo almacena en el InputStream
            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;

            //Lectura con BufferedReader y aplicamos el filtro
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.contains("svchost")) {
                    System.out.println(linea);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
