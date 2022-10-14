package PGV.UT1.Simulacro;

import java.io.IOException;

public class Main {

    private static IOBuffered ioBuffered;
    //Cambiar según la ruta de la carpeta
    private final static String dirAbsolutePath = "C:\\Users\\penga\\Documents\\Development\\IdeaProjects\\IdeaProjects2\\src\\PGV\\UT1\\Simulacro\\NuevaCarpeta";

    public static void main(String[] args) {
        //Construye el proceso msconfig (output cmd)
        ProcessBuilder processBuilder1 = new ProcessBuilder("cmd","/C","msconfig");
        //Construye el proceso Proceso2.jar (creado por nosotros)
        ProcessBuilder processBuilder2 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Simulacro/Proceso2.jar");
        //Construye el proceso dir (ruta del directorio) (output cmd)
        ProcessBuilder processBuilder4 = new ProcessBuilder("cmd","/C","dir "+dirAbsolutePath);

        try {
            //Ejecuta el primer proceso
            Process process1 = processBuilder1.start();
            //Ejecuta el segundo proceso
            Process process2 = processBuilder2.start();

            ioBuffered = new IOBuffered(process2);
            //El proceso 2 (nuestro JAR), está a la espera de una entrada de datos (una ruta, con Scanner)
            //Por lo tanto, le pasamos la ruta de nuestra nueva carpeta.
            ioBuffered.writeLine(dirAbsolutePath);
            //Espera a que termine el proceso 2 para continuar.
            process2.waitFor();

            //Ejecuta el proceso 4.
            Process process4 = processBuilder4.start();

            ioBuffered = new IOBuffered(process4);
            //Lee las lineas que devuelve el comando dir
            for (String line: ioBuffered.readLines()) {
                System.out.println(line);
            }

            //Destruye el proceso 1, pero no funciona, ya que la ejecución es demasiado rápido
            //(Probado con IntelliJ en modo admin, tampoco funciona)
            process1.destroy();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
