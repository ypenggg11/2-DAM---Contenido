package PGV.UT2.ProducerConsumerModel;

import PGV.UT2.ProducerConsumerModel.Model.ClientConsumer;
import PGV.UT2.ProducerConsumerModel.Model.PackageProducer;
import PGV.UT2.ProducerConsumerModel.Model.StorageBuffer;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        //Inicializamos nuestras clases necesarias para el modelo productor-consumidor

        //Buffer, lugar de almacenamiento (listas) donde accederán tanto el productor como el consumidor.
        StorageBuffer storageBuffer = new StorageBuffer();

        //El productor, que accederá al buffer y añadirá (produce) un dato a su almacenamiento.
        //Este se ejecutará en otro hilo, por lo que implementa Runnable y un atributo Thread.
        PackageProducer packageProducer = new PackageProducer(storageBuffer,"Producer 1");

        //El cliente, que accederá al buffer y sacará (consume) un dato de su almacenamiento.
        //Este se ejecutará en otro hilo, por lo que implementa Runnable y un atributo Thread.
        ClientConsumer clientConsumer = new ClientConsumer(storageBuffer,"Consumer 1");

        //Ejecutamos ambos Thread, para que se ejecuten simultáneamente de manera síncrona.
        packageProducer.startThread();
        clientConsumer.startThread();

        //Creamos una instancia de Timer, para ejecutar un TimerTask en el instante que le especifiquemos.
        Timer timer = new Timer();

        //Creamos una instancia de TimerTask, utilizando un lambda.
        // = parámetros -> cuerpo del método;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n||==============================//NEW THREADS EXECUTED//==============================||\n");
                new ClientConsumer(storageBuffer,"Consumer 2").startThread();
                new ClientConsumer(storageBuffer,"Consumer 3").startThread();
                new ClientConsumer(storageBuffer,"Consumer 4").startThread();
            }
        };

        //Ejecutará el método run() del TimerTask después de 10 segundos.
        timer.schedule(timerTask,10000);
    }
}
