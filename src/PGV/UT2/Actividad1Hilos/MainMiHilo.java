package PGV.UT2.Actividad1Hilos;

//Apartado A
public class MainMiHilo {
    public static void main(String[] args){

        int numHilos = 4;
        creaHilos(numHilos);

        System.out.println("Hilo principal terminado.");
    }

    //Crea los objetos de clase MiHilo (implementa la interfaz Runnable)
    private static void creaHilos(int numHilos) {
        for (int i = 0; i< numHilos; i++) {
            MiHilo miHilo = new MiHilo("Hilo con Runnable "+i);

            try {
                //Obtiene el Thread contenido como atributo en la clase,
                //y ejecuta .join() para hacer la ejecución de hilos de forma secuencial.
                //(si no acaba el método run() del Thread anterior, no continúa la ejecución.)
                miHilo.getThread().join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
