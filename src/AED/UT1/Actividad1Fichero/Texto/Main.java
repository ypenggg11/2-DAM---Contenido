package AED.UT1.Actividad1Fichero.Texto;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static File file1;
    private static File file2;
    private static FileReader fileReader;
    private static FileWriter fileWriter;

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        readFile();

        writeFile(input);
    }

    private static void writeFile(Scanner input) throws IOException {
        try {
            file2 = new File("./AED/src/Actividad1Fichero/Texto/Texto2.txt");

            fileWriter = new FileWriter(file2);

            System.out.print("Introduzca lo que quiera escribir: ");
            char[] line = input.nextLine().toCharArray();

            writeLine(line);
            writeAppendLine("\ny esto es el final.");

        }catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File doesn't exists");
        }
        finally {
            closeWriter();
            input.close();
        }
    }

    private static void readFile() throws IOException {
        try {
            file1 = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("./Actividad1Fichero/Texto/Texto.txt")).getFile());

            fileReader = new FileReader(file1);

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
        char[] ten_char = new char[10];

        //Lee 10 por 10 caracteres
        while ((character = fileReader.read(ten_char)) != -1) {

            System.out.println(new String(ten_char,0,character));

            ten_char = new char[10];
        }

    }

    public static void writeLine(char[] lineInChar) throws IOException {
        fileWriter.write(lineInChar);
        fileWriter.flush();
    }

    public static void writeAppendLine(String line) throws IOException {
        fileWriter.append(line);
        fileWriter.flush();
    }

    public static void closeReader() throws IOException {
        fileReader.close();
    }

    public static void closeWriter() throws IOException {
        fileWriter.close();
    }
}
