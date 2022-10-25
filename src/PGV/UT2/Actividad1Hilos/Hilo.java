package PGV.UT2.Actividad1Hilos;

public class Hilo extends Thread{
    //Define los posibles nombres del hilo como id, como static, para poder usarlos luego como parámetro en el constructor.

    public static final String MAX_PRIORITY_ID = "MAX";
    public static final String MED_PRIORITY_ID = "MED";
    public static final String MIN_PRIORITY_ID = "MIN";

    private String threadID;

    //Constructor que asigna un nombre y una prioridad a este Thread
    public Hilo(String name,int priority) {
        this.setName(name);
        this.setPriority(priority);
        this.threadID = "NO_ID";
    }

    //Igual constructor que el anterior, pero con id como parametro
    public Hilo(String name,int priority,String id) {
        this.setName(name);
        this.setPriority(priority);
        this.threadID = id;
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
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(this.getName()+" TERMINADO.");
    }

    //Cambia de prioridad caso de que coincida el número de iteración con su condición.
    //Luego verifica su identidad mediante su ID y su prioridad, en caso verídico, realiza el cambio.
    private void changePriority(int numIt) {
        int changeWhenPos = 5;

        if (numIt == changeWhenPos) {
            if (this.getThreadID().equals(MIN_PRIORITY_ID)&&this.getPriority()==MIN_PRIORITY) {
                this.setPriority(MAX_PRIORITY);
            }else if (this.getThreadID().equals(MAX_PRIORITY_ID)&&this.getPriority()==MAX_PRIORITY){
                this.setPriority(MIN_PRIORITY);
            }
        }
    }

    public String getThreadID() {
        return threadID;
    }

    public void setThreadID(String threadID) {
        this.threadID = threadID;
    }
}
