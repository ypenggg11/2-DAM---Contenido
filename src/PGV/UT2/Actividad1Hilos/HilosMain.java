package PGV.UT2.Actividad1Hilos;

import java.util.ArrayList;

//Apartado B
public class HilosMain {

    private static final ArrayList<Hilo> hilosList = new ArrayList<>();
    public static void main(String[] args) {

        addHilosToList();
        executeHilos();

        System.out.println("Fin de ejecucion.");
    }

    //Ejecuta los objetos Hilo de la lista 'hilosList' (Hilo hereda de Thread)
    private static void executeHilos() {
        for (Hilo hilo: hilosList) {
            //Ejecuta el hilo (Thread, es decir, llama al método run())
            hilo.start();

            try {
                //Espera hasta terminar la ejecución del hilo anterior.
                hilo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Añade objetos Hilo a la lista (añadiendo prioridades con constantes de la clase Thread)
    private static void addHilosToList() {
        hilosList.add(new Hilo("Hilo 1",Thread.MIN_PRIORITY,Hilo.MIN_PRIORITY_ID));
        hilosList.add(new Hilo("Hilo 2",Thread.NORM_PRIORITY));
        hilosList.add(new Hilo("Hilo 3",Thread.MAX_PRIORITY,Hilo.MAX_PRIORITY_ID));
    }
}
