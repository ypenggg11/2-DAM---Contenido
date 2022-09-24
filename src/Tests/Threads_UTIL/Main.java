package Tests.Threads_UTIL;

public class Main{

    /*
    	thread =	A thread of execution in a program (kind of like a virtual CPU)
    				The JVM allows an application to have multiple threads running concurrently
    				Each thread can execute parts of you code in parallel with the main thread
    				Each thread has a priority.
    				Threads with higher priority are executed in preference compared to threads with a lower priority

    			    The Java Virtual Machine continues to execute threads until either of the following occurs
    					1. The exit method of class Runtime has been called
    					2. All user threads have died

    				When a JVM starts up, there is a thread which calls the main method
    				This thread is called “main”

    				Daemon thread is a low priority thread that runs in background to perform tasks such as garbage collection
				JVM terminates itself when all user threads (non-daemon threads) finish their execution
    	 */

    public static void main(String[] args) throws InterruptedException{

        //Another example with GUI
        new GUI();

        System.out.println("Number of active Threads: "+Thread.activeCount());

        Thread.currentThread().setName("MAIN");

        System.out.println("Thread name: "+Thread.currentThread().getName());

        Thread.currentThread().setPriority(10);
        System.out.println("Thread priority: "+Thread.currentThread().getPriority());

        System.out.println("Thread is alive?: "+Thread.currentThread().isAlive());


    	for(int i =3;i>0;i--) {
    		System.out.println(i);

            //Acts like a timer, stops execution for 1s (1000 millis)
    		Thread.sleep(1000);
    	}

    	System.out.println("You are done!");


        MyThread thread2 = new MyThread();

        thread2.setName("2nd thread");
        System.out.println("Thread name: "+thread2.getName());

        thread2.setDaemon(true);
        System.out.println("Thread is Daemon?: "+thread2.isDaemon());

        //Executes the run method of the Thread class
        thread2.start();

        System.out.println("Thread is alive?: "+thread2.isAlive());

        thread2.setPriority(1);
        System.out.println("Thread priority: "+thread2.getPriority());

        System.out.println("Number of active Threads: "+Thread.activeCount());
    }
}