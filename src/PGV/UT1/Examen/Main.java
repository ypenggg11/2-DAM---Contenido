package PGV.UT1.Examen;

import PGV.UT1.Examen.IO_Management.IOBuffered;
import PGV.UT1.Examen.IO_Management.IOBytes;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    //Directorio actual como ruta absoluta.
    private final static String CURRENT_ABSOLUTE_PATH = new File("").getAbsolutePath();
    //Posición del array list donde se encuentra la primera línea de conexión de red (getmac.exe)
    private final static int FIRST_CONECTION_POS = 5;

    public static void main(String[] args) {

        try {
            //Ejecución del primer proceso.
            Process process = new ProcessBuilder("cmd","/C",
                    CURRENT_ABSOLUTE_PATH+"\\src\\PGV\\UT1\\Examen\\getmac.bat").start();

            //Ejecución del segundo proceso.
            Process process2 = new ProcessBuilder("java","-jar","./src/PGV/UT1/Examen/DireccionFisicaMAC.jar").start();

            //Clase personalizada que permite la lectura/escritura de un proceso mediante BufferedReader/Writer
            IOBuffered ioBuffered = new IOBuffered(process);

            //Lee las líneas que devuelve la ejecución del .bat y lo almacena en un array list.
            ArrayList<String> readedLines = getLines(ioBuffered);

            //Clase personalizada que permite la lectura/escritura de un proceso por bytes. (InputStream/OutputStream)
            IOBytes ioBytes = new IOBytes(process2);

            //Pasa al proceso hijo, la línea donde se encuentra la primera línea de conexión de red.
            ioBytes.writeStream(readedLines.get(FIRST_CONECTION_POS));
            //Muestra por pantalla el contenido leído.
            System.out.println("DIRECCIÓN FÍSICA MAC: "+ioBytes.readStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static ArrayList<String> getLines(IOBuffered ioBuffered) {
        ArrayList<String> readedLines = ioBuffered.readLines();

        System.out.println("||====================================||OUTPUT GETMAC.EXE||====================================||\n");
        //Imprime todas las líneas leídas.
        for (String line: readedLines) {
            System.out.println(line);
        }
        System.out.println();

        return readedLines;
    }
}
