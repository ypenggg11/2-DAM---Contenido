package PGV.UT2.Examen.Model;

import PGV.UT2.Examen.Coche;

import java.util.ArrayList;
import java.util.Random;

//Productor
public class Comercial implements Runnable{

    private final Concesionario concesionario;
    private ArrayList<Coche> modelos;
    private final Thread thread;

    public Comercial(Concesionario buffer, String name) {
        this.concesionario = buffer;
        this.thread = new Thread(this);
        this.thread.setName(name);

        modelos = new ArrayList<>();
        initCoches();
    }

    //Inicializa los coches que aportará el comercial, el nombre de sus modelos y su stock inicial
    private void initCoches() {
        Random random  =new Random();

        for (int i = 0;i<random.nextInt(1,5);i++) {
            modelos.add(new Coche(this.thread.getName()+" "+i, (int) (Math.random()*5)));
        }
    }

    @Override
    public void run() {
        //Alta de los modelos a vender
        modelos.forEach(c -> concesionario.push(c,0));

        //Luego de un tiempo, irá proporcionando stock de manera aleatoria
        while (true) {
            try {

                Thread.sleep((long) (Math.random()*8000));

                //De un modelo aleatorio, incrementa un stock aleatorio
                int modPos = (int) (Math.random()* modelos.size());
                int incrementa = (int) (Math.random()*5);

                //Si el incremento es mayor de 0, incrementa en el coche de la lista del buffer
                if (incrementa>0){
                    System.out.println("Stock de "+modelos.get(modPos).getModelo() + " incrementa en "+ incrementa);
                    concesionario.push(modelos.get(modPos),incrementa);
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
