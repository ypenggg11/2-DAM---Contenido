package PGV.UT2.Actividad3FicherosSemaforos.producer_consumer_model;

import PGV.UT2.Actividad3FicherosSemaforos.util.BufferedIO;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FileBuffer {

    private final Semaphore mutex = new Semaphore(1);
    private final BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private final String filePath;

    public FileBuffer(String filePath) {
        this.filePath = filePath;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String line) {
        try {
            mutex.acquire();

            bufferedWriter = new BufferedWriter(new FileWriter(filePath,true));

            bufferedWriter.append(line);
            bufferedWriter.newLine();

            bufferedWriter.close();

            mutex.release();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine(){

        try {
            mutex.acquire();
            String line;

            if ((line = bufferedReader.readLine())!=null) {

                mutex.release();
                return line;
            }

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        mutex.release();
        return null;
    }

}
