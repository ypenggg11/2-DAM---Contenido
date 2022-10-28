package PGV.Tests.UT2_Practica1;

import java.util.concurrent.Semaphore;

public class MyBuffer {

    private int[] arrayImpares;
    int var1 = 0, var2 = 0;

    Semaphore mutex = new Semaphore(1);

    public MyBuffer(int num_items) {
        this.arrayImpares = new int[num_items];
    }

    public /*synchronized*/ boolean cargar(int valor) {

        try {
            mutex.acquire();

            if (var1 < arrayImpares.length - 1) {
                var1++;
                arrayImpares[var1] = valor;

                System.out.println("El hilo " + Thread.currentThread().getName() + " ha cargado el valor " + valor);
                return true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mutex.release();
        return false;
    }

    public /*synchronized*/ void leer() {
        try {
            mutex.acquire();

            if (var1 > var2) {
                var2++;
                System.out.println("El hilo " + Thread.currentThread().getName() + " ha leido el valor " + arrayImpares[var2]);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        mutex.release();
    }
}
