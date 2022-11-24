package PGV.UT2.Examen.Model;

import PGV.UT2.Examen.Coche;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Concesionario {

    private final Semaphore mutex = new Semaphore(1);

    //Lista para almacenar los distintos coches que meteran los comerciales
    private final ArrayList<Coche> coches = new ArrayList<>();

    public Concesionario() {
    }

    public void push(Coche coche, int incremento) {
        try {
            mutex.acquire();

            //Si contiene ya el coche, sobreescribe su cantidad, si no contiene, solo lo añade
            if (coches.contains(coche)) {

                int pos = coches.indexOf(coche);
                int oldStock = coches.get(pos).getStock();
                coches.get(pos).addStock(incremento);

                System.out.println(coche.getModelo() + " se actualizo de " + oldStock + " a " + coches.get(pos).getStock() + " de stock");

            } else {
                coches.add(coche);
                System.out.println(coche.getModelo() + " aniadido al concesionario");
            }

            mutex.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Coche pull(int cochePos) {
        try {
            mutex.acquire();

            Coche coche;

            //Si el stock del coche pedido por el cliente es mayor a 0, decrementa el stock y lo devuelve
            if (coches.get(cochePos).getStock()>0) {
                coche = coches.get(cochePos);
                int oldStock = coche.getStock();
                coches.get(cochePos).decrementStock();

                System.out.println("Se ha comprado correctamente "+coche.getModelo());
                System.out.println(coches.get(cochePos).getModelo()+" ha pasado de " + oldStock + " a " + coches.get(cochePos).getStock() + " de stock");

                mutex.release();
                return coche;
            }

            //Si no queda stock, devuelve nulo
            System.out.println("No queda stock de "+coches.get(cochePos).getModelo());
            mutex.release();
            return null;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Coche> getCoche() {
        return coches;
    }
}
