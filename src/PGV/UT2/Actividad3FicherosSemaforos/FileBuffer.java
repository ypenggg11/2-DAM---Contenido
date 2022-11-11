package PGV.UT2.Actividad3FicherosSemaforos;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FileBuffer {

    private final Semaphore mutex = new Semaphore(1);
    private final BufferedIO bufferedIO;
    private ArrayList<String> linesList;
    private int readLinePosition = 0;

    FileBuffer(String filePath) {
        bufferedIO = new BufferedIO(filePath);

        refreshLinesList();
    }

    private void refreshLinesList() {
        linesList = bufferedIO.readFile();
    }

    public void writeLine(String line) {
        try {
            mutex.acquire();

            bufferedIO.writeFile(line,true,true);
            refreshLinesList();

            mutex.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String readLine(){

        try {
            mutex.acquire();

            if (readLinePosition<linesList.size()) {
                readLinePosition++;

                mutex.release();
                return linesList.get(readLinePosition-1);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mutex.release();
        return null;
    }

    public int getReadLinePosition() {
        return readLinePosition;
    }

    public ArrayList<String> getLinesList() {
        return linesList;
    }
}
