package AED.Actividad1Fichero.Buffered;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static File fileToRead;
    private static File fileToWrite;
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        readFile();

        writeFile(input);
    }

    private static void writeFile(Scanner input) throws IOException {
        try {
            fileToWrite = new File("./AED/src/Actividad1Fichero/Buffered/Texto2.txt");

            fileWriter = new FileWriter(fileToWrite);

            bufferedWriter = new BufferedWriter(fileWriter);

            System.out.print("Introduzca lo que quiera escribir: ");
            String line = input.nextLine();

            for (int i=1;i<=10;i++) {

                writeLine(line, i);
            }

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
            fileToRead = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("./Actividad1Fichero/Buffered/Texto.txt")).getFile());

            fileReader = new FileReader(fileToRead);

            bufferedReader = new BufferedReader(fileReader);

            readLines();

        }catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File doesn't exists");
        }
        finally {
            closeReader();
        }
    }

    public static void readLines() throws IOException {

        String line;

        while ((line = bufferedReader.readLine()) != null) {

            System.out.println(line);
        }

    }

    public static void writeLine(String line,int numFila) throws IOException {
        bufferedWriter.write("Fila "+numFila+": ");
        bufferedWriter.append(line);
        bufferedWriter.flush();
        bufferedWriter.newLine();
    }

    public static void closeReader() throws IOException {
        bufferedReader.close();
        fileReader.close();
    }

    public static void closeWriter() throws IOException {
        bufferedWriter.close();
        fileWriter.close();
    }
}
