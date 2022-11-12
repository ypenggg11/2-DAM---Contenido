package PGV.UT2.Actividad3FicherosSemaforos.producer_consumer_model;

//Producer
public class Writer implements Runnable{

    private final Thread thread;
    private final FileBuffer fileBuffer;

    public Writer(FileBuffer fileBuffer, String threadName){
        this.thread = new Thread(this);
        this.fileBuffer = fileBuffer;

        this.thread.setName(threadName);
    }

    @Override
    public void run() {
        while (true) {
            fileBuffer.writeLine("Linea de "+thread.getName());

            System.out.print("\033[0;32m");
            System.out.println("[ESCRITO] -> Por "+thread.getName());

            try {
                Thread.sleep((long) (Math.random()*2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Thread getThread() {
        return thread;
    }

    public FileBuffer getFileBuffer() {
        return fileBuffer;
    }
}
