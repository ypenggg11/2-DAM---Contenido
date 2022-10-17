package Tests.Threads_UTIL;

//Another form to create Threads, better than the extends Thread one.
public class MyRunnableThread implements Runnable{

    private final Thread thread;

    public MyRunnableThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0;i<10;i++) {
            System.out.println("Runnable Iteration number: "+(i+1));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //Executes when another thread it's done with their job (more time to wait)
//        try {
//            thread.join(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
