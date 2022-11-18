package PGV.UT2.Simulacro;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Buffer {

    private final ArrayList<String> lineasAereas = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);
    public static final int MAX_VUELOS = 50;
    public static final int MIN_VUELOS = 0;

    private int lineasEnCola = 0;

    public Buffer(){
    }

    public boolean push(String lineaAerea) {
        try {
            semaphore.acquire();

            if (lineasEnCola<MAX_VUELOS){
                lineasAereas.add(lineaAerea);
                lineasEnCola++;

                semaphore.release();
                return true;
            }

            semaphore.release();
            return false;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String pull() {
        try {
            semaphore.acquire();

            if (lineasEnCola > MIN_VUELOS) {
                String lineaAerea = lineasAereas.get(0);
                lineasAereas.remove(0);
                lineasEnCola--;

                semaphore.release();
                return lineaAerea;
            }

            semaphore.release();
            return null;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized int getLineasEnCola() {
        return lineasEnCola;
    }
}
