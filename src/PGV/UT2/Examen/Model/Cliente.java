package PGV.UT2.Examen.Model;

import PGV.UT2.Examen.Coche;

//Consumidor
public class Cliente implements Runnable{

    private final Concesionario concesionario;
    private final Thread thread;

    public Cliente(Concesionario buffer, String name) {
        this.concesionario = buffer;
        this.thread = new Thread(this);
        this.thread.setName(name);
    }

    @Override
    public void run() {
        boolean comprado = false;
        int intentos = 0;
        Coche coche;

        //Mientras no haya comprado...
        while (!comprado) {
            //El coche aleatorio que quiere el cliente
            int cochePos = (int) (Math.random()*concesionario.getCoche().size());
            System.out.println(this.thread.getName()+" intenta comprar "+concesionario.getCoche().get(cochePos).getModelo());

            try {
                //Si queda stock y devuelve el coche
                if ((coche = concesionario.pull(cochePos))!=null) {

                    //Abandona el concesionario
                    comprado = true;
                }else {
                    //Si no hay stock, aumenta sus intentos, y al segundo intento fallido, se va
                    intentos++;
                    if (intentos<2){
                        Thread.sleep((long) (Math.random()*6000));
                    }else {
                        System.out.println(this.thread.getName()+" agoto sus intentos");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void runThread() {
        this.thread.start();
    }

    public Thread getThread() {
        return thread;
    }
}
