package AED.Actividad1Fichero.ListasDeFicheros;

import java.io.File;
import java.util.Objects;

public class Main {

    private static File file;
    private static String[] fileList;

    public static void main(String[] args) {

        new GUI();

        file = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("./")).getFile());
        fileList = file.list();

        for (String fileName: fileList) {
            System.out.println(fileName);
        }
    }
}
