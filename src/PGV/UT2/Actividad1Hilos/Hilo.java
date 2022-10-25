package PGV.UT2.Actividad1Hilos;

public class Hilo extends Thread{
    //Constructor que asigna un nombre y una prioridad a este Thread
    public Hilo(String name,int priority) {
        this.setName(name);
        this.setPriority(priority);
    }

    //Sobreescribe el método run() que contiene la clase padre Thread
    //(A su vez, Thread implementa la interfaz Runnable, que contiene el método run())
    @Override
    public void run() {
        super.run();

        int numIt = 10;

        for (int i=1;i<=numIt;i++) {
            //Imprime el nombre y la prioridad de
            System.out.println(this.getName() +" con prioridad: "+this.getPriority());

            changePriority(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void changePriority(int i) {
        if (i == 5) {
            if (this.getPriority()==Thread.MIN_PRIORITY) {
                this.setPriority(MAX_PRIORITY);
            }else if (this.getPriority()==Thread.MAX_PRIORITY){
                this.setPriority(MIN_PRIORITY);
            }
        }
    }
}
