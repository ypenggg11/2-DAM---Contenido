package PGV.UT2.Examen.Model;

public class Consumer implements Runnable{

    //o FullBuffer
    private final EmptyBuffer emptyBuffer;
    private final Thread thread;

    Consumer(EmptyBuffer buffer, String name) {
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
