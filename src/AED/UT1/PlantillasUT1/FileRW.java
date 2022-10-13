package AED.UT1.PlantillasUT1;

import java.io.*;

public class FileRW {

    private File file;
    private FileWriter fileWriter;
    private FileReader fileReader;

    public FileRW(String path) {
        this.file = new File(path);
    }

    //Lee un conjunto de caracteres (charsToRead) y los devuelve almacenado en una String
    public String readFile(int charsToRead) {
        StringBuffer text = new StringBuffer();

        try {
            this.fileReader = new FileReader(this.file);

            int character;
            char[] numOfChars = new char[charsToRead];

            //Lee el número de caracteres especificados (longitud de numOfChars) y los almacena en ella.
            while ((character = this.fileReader.read(numOfChars)) != -1) {

                text.append(new String(numOfChars, 0, character));

                numOfChars = new char[charsToRead];

                //Separa cada conjunto de chars leidos con un salto de linea.
                text.append("\n");
            }

            this.fileReader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text.toString();
    }

    //Lee el fichero y devuelve su contenido completo en una String
    public String readFile() {
        StringBuffer text = new StringBuffer();

        try {
            this.fileReader = new FileReader(this.file);

            int character;

            //FileReader trabaja en int (con el número del char), y finaliza cuando es -1 el número
            while ((character = this.fileReader.read()) != -1) {
                text.append((char) character);
            }

            this.fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text.toString();
    }

    //Escribe en el fichero, la línea indicada.
    //(append) especifica si se va a sobreescribir el contenido o se une al antiguo.
    public void writeFile(String line, boolean append) {
        try {
            this.fileWriter = new FileWriter(this.file, append);

            this.fileWriter.write(line);
            this.fileWriter.flush();

            this.fileWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }
}
