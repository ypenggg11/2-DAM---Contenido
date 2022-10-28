package PGV.Tests.UT2_Practica1;

public class Productor implements Runnable {

    private final Thread thread = new Thread(this);
    private MyBuffer buffer;

    public Productor(MyBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        boolean exito = true;
        int i = 1;

        while (exito) {
            exito = buffer.cargar(i);

            if (exito) {
                i += 2;
            }

            try {
                Thread.sleep((int) (Math.random() * 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Thread getThread() {
        return thread;
    }
}
