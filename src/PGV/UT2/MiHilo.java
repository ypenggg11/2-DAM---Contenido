package PGV.UT2;

public class MiHilo implements Runnable{

    private final Thread thread;

    public MiHilo(int i) {
        thread = new Thread(this);
        thread.setName("Hilo con Runnable "+i);
        thread.start();
    }

    @Override
    public void run() {
        int numIt = 5;

        for (int i = 0; i< numIt;i++) {
            System.out.println("Iteracion "+i +" del hilo: "+Thread.currentThread().getName());
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(thread.getName()+" TERMINADO.");
    }

    public Thread getThread() {
        return thread;
    }
}
