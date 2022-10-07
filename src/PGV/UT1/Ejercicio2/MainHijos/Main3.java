package PGV.UT1.Ejercicio2.MainHijos;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String serviceName = scanner.nextLine();
        ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/C", "sc qc " + serviceName);

        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();

            int c;
            while((c = inputStream.read()) != -1) {
                System.out.print((char)c);
            }

            inputStream.close();
        } catch (IOException var7) {
            throw new RuntimeException(var7);
        }
    }
}
