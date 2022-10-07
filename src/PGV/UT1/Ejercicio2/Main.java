package PGV.UT1.Ejercicio2;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        ProcessBuilder processBuilder1 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Ejercicio2/ProcesoHijo.jar");

        try {
            Process process = processBuilder1.start();

            OutputStream outputStream = process.getOutputStream();

            outputStream.write("hola mundo!\n".getBytes());
            outputStream.flush();

            outputStream.close();

            InputStream inputStream = process.getInputStream();

            int c;
            while ((c = inputStream.read())!=-1) {
                System.out.print((char)c);
            }

            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProcessBuilder processBuilder2 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Ejercicio2/TaskListHijo.jar");

        try {
            Process process = processBuilder2.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine())!=null) {

                if (line.contains("svchost.exe")) {
                    StringBuffer service = new StringBuffer();
                    bufferedReader.readLine();
                    service.append(bufferedReader.readLine()).append("\n");

                    String[] serviceName = service.toString().split(":");

                    printProcessConfig(serviceName[1].trim());
                }
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printProcessConfig(String serviceName) {
        ProcessBuilder processBuilder3 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Ejercicio2/ServiceConfigHijo.jar");

        try {
            Process process = processBuilder3.start();

            OutputStream outputStream = process.getOutputStream();

            outputStream.write(serviceName.getBytes());
            outputStream.flush();

            outputStream.close();

            InputStream inputStream = process.getInputStream();

            int c;
            while ((c = inputStream.read())!=-1) {
                System.out.print((char)c);
            }

            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
