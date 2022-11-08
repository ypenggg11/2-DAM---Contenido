package PGV.UT2.ProducerConsumerModel.Model;

import java.util.ArrayList;

/**
 * -Funcionamiento del modelo Productor-Consumidor:
 * 1.- Los productores y los consumidores accederán al buffer, y
 *     producirán o consumirán items del almacenamiento del buffer.
 * 2.- Ambos podrán acceder de manera síncrona/asíncrona, dependiendo del tiempo,
 *     pero evitando solapamientos con monitores/semáforos, en distintos hilos de ejecución.
 * 3.- Habrá que evitar añadir items en caso de estar lleno, y evitar sacar items en el caso de vacío.
 */
public class StorageBuffer {

    //Lugar de almacenamiento del buffer (listas/queues)
    private final ArrayList<String> packageList;
    //Número de paquetes en el buffer (para controlar una cantidad máxima/mínima)
    public int numOfPackages = 0;

    //Está lleno el buffer (Llegó a la cantidad máxima -> No podrá añadir paquetes hasta que se saquen antes)
    public boolean isFull;
    //Está vacío el buffer (Llegó a la cantidad mínima -> No podrá sacar paquetes hasta que se añadan antes)
    public boolean isEmpty;

    //Capacidad máxima del almacenamiento.
    public static final int MAX_CAPACITY = 50 + 1;
    //Capacidad mínima del almacenamiento.
    public static final int MIN_CAPACITY = 0;

    public StorageBuffer() {
        this.packageList = new ArrayList<>(MAX_CAPACITY);
        this.isFull = false;
        this.isEmpty = true;
    }

    public StorageBuffer(ArrayList<String> packageList) {
        this.packageList = packageList;
        this.isFull = false;
        this.isEmpty = true;
    }

    /**
     * Añade un item a la lista del buffer.
     * @synchronized Evita que se solapen los métodos synchronized que se ejecuten en distintos hilos.
     * @param packageName Item a añadir.
     */
    public synchronized void addPackage(String packageName) {

        packageList.add(numOfPackages, packageName);
        System.out.println("+ADDED -> Package: " + numOfPackages);

        //Incrementa el número de items.
        numOfPackages++;

        //Al añadir un item,ya no estará vacío.
        this.isEmpty = false;

        //Si llega a la capacidad máxima, estará lleno, en caso contrario, vacío.
        if (numOfPackages == MAX_CAPACITY) {
            this.isFull = true;
        } else {
            this.isFull = false;
        }
    }

    /**
     * Devuelve un item de la lista del buffer.
     * @synchronized Evita que se solapen los métodos synchronized que se ejecuten en distintos hilos.
     * @return El último item añadido a la lista del buffer.
     */
    public synchronized String takePackage() {

        String packageName = packageList.get(numOfPackages - 1);
        packageList.remove(numOfPackages - 1);

        System.out.println("-TAKED by " + Thread.currentThread().getName() + " -> " + packageName);

        //Decrementa el número de items.
        numOfPackages--;
        //Al decrementar, ya no estará lleno.
        this.isFull = false;

        //Si llega a la capacidad mínima, estará vacío, en caso contrario, lleno.
        if (numOfPackages == MIN_CAPACITY) {
            this.isEmpty = true;
        } else {
            this.isEmpty = false;
        }

        return packageName;
    }
}
