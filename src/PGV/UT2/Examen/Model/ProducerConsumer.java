package PGV.UT2.Examen.Model;

import PGV.UT2.Examen.Model.EmptyBuffer;

public class ProducerConsumer implements Runnable{

    //o FullBuffer
    private final EmptyBuffer emptyBuffer;
    private final Thread thread;

    ProducerConsumer(EmptyBuffer buffer, String name) {
        this.emptyBuffer = buffer;
        this.thread = new Thread(this);
        this.thread.setName(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object obj;

                if ((obj = emptyBuffer.pull())!=null) {
                    System.out.println("Pulled" + obj);

                    Thread.sleep((long) (Math.random()*1000));

                    emptyBuffer.push(new Object());
                    System.out.println("Pushed");
                }else {
                    System.out.println("Not pulled");
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
