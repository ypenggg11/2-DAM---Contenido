package AED.UT1.Simulacro.IOManager;

import java.io.*;
import java.util.ArrayList;

public class Buffered {

    private File file;
    private BufferedReader bufferedReader;
    private FileReader fileReader;
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    public Buffered(String path) {
        this.file = new File(path);

        try {
            fileWriter = new FileWriter(this.file);
            fileReader = new FileReader(this.file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> readFile() {
        ArrayList<String> lines = new ArrayList<>();

        try {
            this.bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null){
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public void closeReader() {
        try {
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String line,boolean newLine,boolean append) {
        try {
            this.bufferedWriter = new BufferedWriter(fileWriter);

            if (newLine){
                bufferedWriter.newLine();
            }
            if (append){
                bufferedWriter.append(line);
            }else {
                bufferedWriter.write(line);
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeWriter() {
        try {
            fileWriter.close();
            bufferedWriter.close();
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

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }
}
