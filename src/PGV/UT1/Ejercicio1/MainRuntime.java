package PGV.UT1.Ejercicio1;

import java.io.IOException;

public class MainRuntime {

    private static Runtime runtime;

    public static void main(String[] args) {
        //Inicializa el objeto runtime de clase Runtime
        runtime = Runtime.getRuntime();

        //Por comandos del Sistema Operativo
        executeProcess("notepad.exe");

        //Por comandos almacenados en un fichero por lotes (.bat)
        //Ejecuta el siguiente comando en el cmd y devuelve el resultado (proceso)
        executeProcess("cmd /C start .\\src\\PGV\\UT1\\Ejercicio1\\ficheroPorLotes.bat");

        //Ejecución de un .jar
        executeProcess("java -jar .\\src\\PGV\\UT1\\Ejercicio1\\Calculadora.jar");
    }

    private static void executeProcess(String command) {

        Process process;

        try {
            //Ejecuta el proceso pasado por comando
            process = runtime.exec(command);

            //Espera a que sea cerrado el proceso actual para que se ejecute el siguiente (en caso de que haya)
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Devuelve la información del proceso
        System.out.println(process.info());
    }
}
