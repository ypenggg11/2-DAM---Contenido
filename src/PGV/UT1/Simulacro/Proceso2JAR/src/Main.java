package PGV.UT1.Simulacro.Proceso2JAR.src;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static IOBytes iobytes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Espera a una entrada de datos (OutputStream)
        String dir = scanner.nextLine();

        //Para obtener el directorio, creamos una instancia de file, y le pasamos la ruta
        File file = new File(dir);

        //Comprobamos si el fichero es un directorio
        if (file.isDirectory()) {
            //Obtenemos los nombres de los ficheros dentro del directorio
            String[] fileNames = file.list();

            //Instancia del ProcessBuilder, para luego ejecutar el proceso 3 (hijo)
            ProcessBuilder processBuilder3 = new ProcessBuilder("cmd", "/C", "C:\\Users\\penga\\Documents\\Development\\IdeaProjects\\IdeaProjects2\\src\\PGV\\UT1\\Simulacro\\proceso3.bat");

            for (String miFileName : fileNames) {
                //En este caso, no hay directorios, pero es conveniente comprobar si se trata de un archivo
                //(file.listFiles(), y comprobar con file.isFile())
                try {
                    //Ejecuta el ProcessBuilder (proceso3.bat, utilizado ruta absoluta).
                    Process process3 = processBuilder3.start();

                    iobytes = new IOBytes(process3);

                    //Escribimos en el bat (en espera de lectura con 'echo'), utilizado ruta absoluta.
                    //Ejecuta attrib +H (archivo)
                    iobytes.writeStream(dir + "\\" + miFileName);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}