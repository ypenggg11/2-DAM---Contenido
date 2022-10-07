package PGV.UT1.Ejercicio2;

import java.io.*;

/*
Proceso hijo -> scanner.nextLine(); = A la espera de una entrada desde el proceso padre
(OutputStream/BufferedWriter)

Proceso hijo -> System.out.print(); = Devuelve una salida de datos y el proceso padre lo captura
(InputStream/BufferedReader)
 */
public class Main {

    public static void main(String[] args) {
        apartado1();

        apartado2();
    }

    //Lee la lista (tasklist) devuelto de un proceso hijo, y recupera aquellos cuyo nombre sean svchost
    //y posteriormente, el servicio asociado a ese PID
    private static void apartado2() {
        ProcessBuilder processBuilder2 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Ejercicio2/TaskListHijo.jar");

        try {
            Process process = processBuilder2.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine())!=null) {

                if (line.contains("svchost.exe")) {
                    StringBuilder service = new StringBuilder();
                    //Salto de línea, para omitir una linea que no necesitamos
                    bufferedReader.readLine();

                    //Guardamos la línea q nos interesa (la del servicio)
                    service.append(bufferedReader.readLine());

                    //Hacemos un split para obtener el nombre del servicio.
                    String[] serviceName = service.toString().split(":");

                    printServiceConfig(serviceName[1].trim());
                }
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Ejecuta un proceso hijo, donde le pasamos un texto, lo recibirá y devolverá un resultado,
    //posteriormente, recuperamos ese resultado y lo mostramos por consola.
    private static void apartado1() {
        ProcessBuilder processBuilder1 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Ejercicio2/ProcesoHijo.jar");

        try {
            Process process = processBuilder1.start();

            writeStream("servicios svchost.exe\n", process);

            System.out.println(readStream(process));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Pasa el nombre del servicio al hijo, este ejecutará un comando 'sc qc nombre_servicio' y devolverá
    //el resultado de la ejecución de ese comando, y en el padre, lo leeremos y mostraremos por pantalla.
    private static void printServiceConfig(String serviceName) {
        ProcessBuilder processBuilder3 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Ejercicio2/ServiceConfigHijo.jar");

        try {
            Process process = processBuilder3.start();

            writeStream(serviceName, process);

            System.out.println(readStream(process));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Lectura (en bytes) de los datos recuperados en el proceso hijo.
    private static String readStream(Process process) throws IOException {
        StringBuilder text = new StringBuilder();

        InputStream inputStream = process.getInputStream();

        int c;
        while ((c = inputStream.read())!=-1) {
            text.append((char) c);
        }

        inputStream.close();

        return text.toString();
    }

    //Escritura (en bytes) desde el padre, hacia el hijo.
    private static void writeStream(String line, Process process) throws IOException {
        OutputStream outputStream = process.getOutputStream();

        outputStream.write(line.getBytes());
        outputStream.flush();

        outputStream.close();
    }
}
