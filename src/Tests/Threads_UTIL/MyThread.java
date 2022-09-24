package Tests.Threads_UTIL;

public class MyThread extends Thread{

    //run method is executed when you use the start() method of the thread
    @Override
    public void run() {

        //Checks if it's a Daemon thread
        if(this.isDaemon()) {
            System.out.println("This is a daemon thread that is running");
        }
        else {
            System.out.println("This is a user thread that is running");
        }
    }
}
