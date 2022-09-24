package AED.Actividad1Fichero.FicherosBinarios;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main {

    private static File file;
    private static FileInputStream fileInputStream;
    private static FileOutputStream fileOutputStream;

    public static void main(String[] args) throws IOException {

        writeFile();

        readFile();
    }

    private static void writeFile() throws IOException {
        try {
            file = new File("./AED/src/Actividad1Fichero/FicherosBinarios/Texto.txt");

            fileOutputStream = new FileOutputStream(file);

            for (int i=1;i<=50;i++) {

                writeLine(i);
            }

            String finalLine = "++++++++++";
            byte[] b = finalLine.getBytes(StandardCharsets.UTF_8);

            fileOutputStream.write(b);
            fileOutputStream.flush();

        }catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File doesn't exists");
        }
        finally {
            closeWriter();
        }
    }

    private static void readFile() throws IOException {
        try {
            file = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("./Actividad1Fichero/FicherosBinarios/Texto.txt")).getFile());

            fileInputStream = new FileInputStream(file);

            readLines();

        }catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File doesn't exists");
        }
        finally {
            closeReader();
        }
    }

    public static void readLines() throws IOException {

       int character;

       while ((character = fileInputStream.read())!=-1) {
        System.out.print((char)character);
       }
    }

    public static void writeLine(int numIte) throws IOException {
        String line = "Iteración "+numIte+"\n";

        fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
    }

    public static void closeReader() throws IOException {
        fileInputStream.close();
    }

    public static void closeWriter() throws IOException {
        fileOutputStream.close();
    }
}
