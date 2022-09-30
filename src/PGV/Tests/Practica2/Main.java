package PGV.Tests.Practica2;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        filterTaskList();

        dateInputAndOutput();
    }

    private static void dateInputAndOutput() {
        BufferedWriter bufferedWriter;
        InputStream inputStream;
        BufferedReader bufferedReader;
        Process process;
        OutputStream outputStream;
        try {
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

    private static void filterTaskList() {
        Process process;
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            process = new ProcessBuilder("cmd", "/C", "tasklist").start();

            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;

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
