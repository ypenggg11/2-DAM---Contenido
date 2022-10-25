package PGV.UT2;

public class MainMiHilo {
    public static void main(String[] args) throws InterruptedException {

        int numHilos = 4;

        for (int i = 0;i<numHilos;i++) {
            MiHilo miHilo = new MiHilo(i);

            miHilo.getThread().join();
        }

        System.out.println("Hilo principal terminado.");
    }
}
