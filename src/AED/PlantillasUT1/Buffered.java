package AED.PlantillasUT1;

import java.io.*;
import java.util.Objects;

public class Buffered {

    private File file;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Buffered(String path) {
        this.file = new File(path);
    }

    public void readFile() {
        try {
            this.bufferedReader = new BufferedReader(new FileReader(this.file));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String line,boolean newLine,boolean append) {
        try {
            this.bufferedWriter = new BufferedWriter(new FileWriter(this.file));

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
