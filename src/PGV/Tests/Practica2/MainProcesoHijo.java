package PGV.Tests.Practica2;

import java.io.*;

public class MainProcesoHijo {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        try{
            Process process = runtime.exec("java -jar ./src/PGV/Tests/Practica2/ProcesoHijo.jar");

            OutputStream outputStream = process.getOutputStream();

            outputStream.write("Peng\n".getBytes());
            outputStream.flush();

            outputStream.close();

            InputStream inputStream = process.getInputStream();

            int c;
            while ((c = inputStream.read())!=-1) {
                System.out.print((char)c);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
