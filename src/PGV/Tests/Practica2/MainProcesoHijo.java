package PGV.Tests.Practica2;

import java.io.*;

public class MainProcesoHijo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        try{
            Process process = runtime.exec("java -jar ");

            OutputStream outputStream = process.getOutputStream();

            outputStream.write("".getBytes());
            outputStream.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
