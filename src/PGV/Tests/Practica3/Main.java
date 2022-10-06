package PGV.Tests.Practica3;

import java.io.*;

//P1 ejecuta ipconfig (p2)
//P1 debe esperar a que p2 acabe
//p1 extrae ip propia
//p1 envia ip a p3
//p3 lee la ip y ejecuta p4 (ping ip)
public class Main {

    public static void main(String[] args) {
        String ip = ExecuteProcess2();

        BufferedReader bufferedReader;
        InputStream inputStream;
        OutputStream outputStream;
        BufferedWriter bufferedWriter;

        Process process3;

        try {
            //Ejecuta el comando date en el cmd
            process3 = new ProcessBuilder("").start();

            inputStream = process3.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            outputStream = process3.getOutputStream();
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            String linea;

            //Tienes que ponerlo encima ya que la velocidad de lectura es demasiado rápido

            bufferedWriter.write(ip);
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

    private static String ExecuteProcess2() {
        Runtime runtime = Runtime.getRuntime();
        InputStream inputStream;
        BufferedReader bufferedReader;

        try {
            Process process = runtime.exec("java -jar ./src/PGV/Tests/Practica3/ipconfig.jar");
            process.waitFor();

            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String linea,ip = "";

            while ((linea = bufferedReader.readLine()) != null) {

                if (linea.contains("IPv4")) {
                    ip = linea.substring(47);
                }
            }

            return ip;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
