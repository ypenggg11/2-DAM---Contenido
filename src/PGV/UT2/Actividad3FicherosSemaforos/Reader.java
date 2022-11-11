package PGV.UT2.Actividad3FicherosSemaforos;

//Consumer
public class Reader implements Runnable{

    private final Thread thread;
    private final FileBuffer fileBuffer;

    Reader(FileBuffer fileBuffer,String threadName){
        this.thread = new Thread(this);
        this.fileBuffer = fileBuffer;

        this.thread.setName(threadName);
    }

    @Override
    public void run() {
        while (true) {
            String line;
            if ((line=fileBuffer.readLine())!=null) {
                System.out.println("LEIDO: " + line);
            }

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
