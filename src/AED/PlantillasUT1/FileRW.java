package AED.PlantillasUT1;

import java.io.*;
import java.util.Objects;

public class FileRW {

    private File file;
    private FileWriter fileWriter;
    private FileReader fileReader;

    public FileRW(String path) {
        this.file = new File(path);
    }

    public void readFile(int charsToRead) {
        try {
            this.fileReader = new FileReader(this.file);

            int character;
            char[] numOfChars = new char[charsToRead];

            while ((character = fileReader.read(numOfChars)) != -1) {

                System.out.println(new String(numOfChars,0,character));

                numOfChars = new char[charsToRead];
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile() {
        try {
            this.fileReader = new FileReader(this.file);

            int character;

            while ((character = fileReader.read()) != -1){
                System.out.print(character);
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String line,boolean append) {
        try {
            this.fileWriter = new FileWriter(this.file);

            if (append){
                fileWriter.append(line);
                fileWriter.flush();
            }else {
                fileWriter.write(line);
                fileWriter.flush();
            }

            fileWriter.close();
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
