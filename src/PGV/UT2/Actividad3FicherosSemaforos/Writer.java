package PGV.UT2.Actividad3FicherosSemaforos;

import java.util.Random;

//Producer
public class Writer implements Runnable{

    private final Thread thread;
    private final FileBuffer fileBuffer;

    Writer(FileBuffer fileBuffer,String threadName){
        this.thread = new Thread(this);
        this.fileBuffer = fileBuffer;

        this.thread.setName(threadName);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("ESCRITO por "+thread.getName());
            fileBuffer.writeLine("Linea de "+thread.getName());

            try {
                Thread.sleep((long) (Math.random()*1500));
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
