package AED.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

public class Buffered {

    private File file;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Buffered(String path) {
        this.file = new File(path);

        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.file));
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> readFile() {
        ArrayList<String> linesList = new ArrayList<>();

        try {
            String line;

            while ((line = bufferedReader.readLine()) != null){
                linesList.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return linesList;
    }

    public void writeFile(String line,boolean newLine,boolean append) {
        try {

            if (newLine){
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            if (append){
                bufferedWriter.append(line);
                bufferedWriter.flush();
            }else {
                bufferedWriter.write(line);
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeWriter() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeReader() {
        try {
            bufferedReader.close();
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
