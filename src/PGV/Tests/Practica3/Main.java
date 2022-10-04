package PGV.Tests.Practica3;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        Process process;

        InputStream inputStream;
        BufferedReader bufferedReader;

        try {
            //Ejecuta el comando date en el cmd
            process = new ProcessBuilder("cmd", "/C", "ipconfig").start();


            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String ip = "";
                
                if (linea.contains("IPv4")) {
                    ip = linea;System.out.println(ip.substring(47));

                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
