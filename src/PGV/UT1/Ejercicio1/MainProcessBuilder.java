package PGV.UT1.Ejercicio1;

import java.io.IOException;

public class MainProcessBuilder {

    private static ProcessBuilder processBuilder;

    public static void main(String[] args) {

        //Lo puesto en los parámetros o en el método .command, ejecuta lo puesto en el interior en consola

        //Por comandos del Sistema Operativo
        processBuilder = new ProcessBuilder().command("notepad.exe");

        executeProcess(processBuilder);

        //Por comandos almacenados en un fichero por lotes (.bat)
        //cmd /C start (separados por comas) -> abre el cmd
        processBuilder = new ProcessBuilder("cmd","/C","start",".\\src\\PGV\\UT1\\Ejercicio1\\ficheroPorLotes.bat");

        executeProcess(processBuilder);

        //Ejecución de un .jar
        //Equivalente a ejecutar en el cmd -> java -jar ruta\archivo.jar
        processBuilder = new ProcessBuilder("java","-jar",".\\src\\PGV\\UT1\\Ejercicio1\\Calculadora.jar");

        executeProcess(processBuilder);
    }

    private static void executeProcess(ProcessBuilder processBuilder) {

        Process process;

        try {
            //Ejecuta el proceso pasado por comando
            process = processBuilder.start();

            //Espera a que sea cerrado el proceso actual para que se ejecute el siguiente (en caso de que haya)
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Devuelve la información del proceso
        System.out.println(process.info());
    }
}
