package PGV.UT2;

import java.util.ArrayList;

public class HilosMain {
    public static void main(String[] args) {

        ArrayList<Hilo> hilosList = new ArrayList<>();

        hilosList.add(new Hilo("Hilo 1",Thread.MIN_PRIORITY));
        hilosList.add(new Hilo("Hilo 2",Thread.NORM_PRIORITY));
        hilosList.add(new Hilo("Hilo 3",Thread.MAX_PRIORITY));

        for (Hilo hilo: hilosList) {
            hilo.start();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("Fin de ejecucion.");
    }
}
