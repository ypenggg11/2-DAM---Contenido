package PGV.UT2.Examen.Model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class EmptyBuffer {

    private final Semaphore mutex = new Semaphore(1);

    private final ArrayList<Object> list = new ArrayList<>();
    public static final int MAX = 20;
    public static final int MIN = 0;

    EmptyBuffer(){
    }

    public boolean push(Object obj) {
        try {
            mutex.acquire();

            if (list.size()<MAX){
                list.add(obj);

                mutex.release();
                return true;
            }

            mutex.release();
            return false;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Object pull() {
        try {
            mutex.acquire();

            if (list.size()>MIN){
                Object object = list.get(0);
                list.remove(0);

                mutex.release();
                return object;
            }

            mutex.release();
            return null;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Object> getList() {
        return list;
    }
}
