package Tests.TimerAndTasks_UTIL;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    // Timer = 		A facility for threads to schedule tasks
    //				for future execution in a background thread

    // TimerTask = 	A task that can be scheduled for one-time
    //				or repeated execution by a Timer

    public static void main(String[] args) {

        //Another example with GUI
        new GUI();

        //A timer for the timer task
        Timer timer = new Timer();

        //Anonymous inner class (Can add data and override methods when you declare it)

        //A task that executes by the timer
        TimerTask task = new TimerTask() {

            char[] hola = "Hola Mundo!".toCharArray();
            int counter = 0;

            //When the timer finishes, it will call this run method
            @Override
            public void run() {
                if(counter<hola.length) {
                    System.out.print(hola[counter]);
                    counter++;
                }
                else {
                    timer.cancel();
                }
            }
        };

        //How to make a calendar (date)
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR,2022);
        date.set(Calendar.MONTH,Calendar.SEPTEMBER);
        date.set(Calendar.DAY_OF_MONTH,24);
        date.set(Calendar.HOUR_OF_DAY,15);
        date.set(Calendar.MINUTE,38);
        date.set(Calendar.SECOND,30);
        date.set(Calendar.MILLISECOND,0);

        //After 2 seconds, prints Hello World, char by char, every 1s.
        timer.scheduleAtFixedRate(task, 2000,1000);

        //timer.schedule(task, 0); -> (task, delay to execute)
        //timer.schedule(task, date.getTime()); -> (task, date to execute)
        //timer.scheduleAtFixedRate(task, 0, 1000); -> (task, delay/date, every how much time calls the run method)
        //timer.scheduleAtFixedRate(task, date.getTime(), 1000); //period works on milliseconds, 1000 = 1 s
    }

}
