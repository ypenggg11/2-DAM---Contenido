package PGV.UT2.Actividad1Hilos;

public class MiHilo implements Runnable{

    private final Thread thread;

    //Constructor
    public MiHilo(String name) {
        //Inicializa el Thread, haciendo referencia a una clase con un método run(),
        //que implementa la interfaz Runnable y, por tanto, le pasamos 'this' por como parámetro.
        thread = new Thread(this);
        thread.setName(name);
        //Ejecuta el Thread (llamará al método run de la clase a la que hace referencia 'this')
        thread.start();
    }

    //Método que implementa la interfaz Runnable y llamado por el método '.start()' de un Thread.
    @Override
    public void run() {
        int numIt = 5;

        //Muestra el nombre del hilo (Thread) actual en ejecución.
        for (int i = 0; i< numIt;i++) {
            System.out.println("Iteracion "+i +" del hilo: "+Thread.currentThread().getName());
        }

        //Duerme el hilo por 0,5 seg, para evitar el solapamiento de datos durante la ejecución.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Indica que ha terminado la ejecución del Thread (terminó el método run())
        System.out.println(thread.getName()+" TERMINADO.");
    }

    public Thread getThread() {
        return thread;
    }
}
