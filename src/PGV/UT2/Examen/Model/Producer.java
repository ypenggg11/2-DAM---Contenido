package PGV.UT2.Examen.Model;

import PGV.UT2.Examen.Model.EmptyBuffer;

public class Producer implements Runnable{

    //o FullBuffer
    private final EmptyBuffer emptyBuffer;
    private final Thread thread;

    Producer(EmptyBuffer buffer, String name) {
        this.emptyBuffer = buffer;
        this.thread = new Thread(this);
        this.thread.setName(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (emptyBuffer.push(new Object())) {
                    System.out.println("Pushed");
                }else {
                    System.out.println("Not pushed");
                }

                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void runThread() {
        this.thread.start();
    }

    public Thread getThread() {
        return thread;
    }
}
