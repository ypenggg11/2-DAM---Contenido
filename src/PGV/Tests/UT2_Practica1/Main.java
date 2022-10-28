package PGV.Tests.UT2_Practica1;

public class Main {

    private final static int productores = 1, consumidores = 3, num_items = 50;

    public static void main(String[] args) {

        MyBuffer buffer = new MyBuffer(num_items);

        for (int i = 0; i < productores; i++) {
            Productor productor = new Productor(buffer);
            productor.getThread().setName("Hilo productor: " + i);
            productor.getThread().start();
        }

        for (int i = 0; i < consumidores; i++) {
            Consumidor consumidor = new Consumidor(buffer);
            consumidor.getThread().setName("Hilo consumidor: " + i);
            consumidor.getThread().start();
        }

    }
}
