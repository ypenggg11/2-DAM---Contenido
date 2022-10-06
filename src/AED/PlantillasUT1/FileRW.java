package AED.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

public class FileRW {

    private File file;
    private FileWriter fileWriter;
    private FileReader fileReader;

    public FileRW(String path) {
        this.file = new File(path);

        try {
            this.fileReader = new FileReader(this.file);
            this.fileWriter = new FileWriter(this.file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFile(int charsToRead) {
        StringBuilder text = new StringBuilder();

        try {

            int character;
            char[] numOfChars = new char[charsToRead];

            while ((character = fileReader.read(numOfChars)) != -1) {

                text.append(new String(numOfChars,0,character));

                numOfChars = new char[charsToRead];
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text.toString();
    }

    public String readFile() {
        StringBuilder text= new StringBuilder();

        try {

            int character;

            while ((character = fileReader.read()) != -1){
                text.append((char) character);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text.toString();
    }

    public void writeFile(String line,boolean append) {
        try {

            if (append){
                fileWriter.append(line);
                fileWriter.flush();
            }else {
                fileWriter.write(line);
                fileWriter.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeWriter() {
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeReader() {
        try {
            fileReader.close();
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
