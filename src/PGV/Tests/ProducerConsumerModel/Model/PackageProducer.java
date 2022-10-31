package PGV.Tests.ProducerConsumerModel.Model;

import PGV.Tests.ProducerConsumerModel.ExecuteThread;

public class PackageProducer implements Runnable, ExecuteThread {

    //Se ejecutará sobre un hilo nuevo.
    private final Thread producerThread = new Thread(this);
    //Buffer al que accederemos para añadir items.
    private StorageBuffer storageBuffer;
    private String producerName;

    public PackageProducer(StorageBuffer buffer, String producerName) {
        this.storageBuffer = buffer;
        this.producerName = producerName;
        this.producerThread.setName(producerName);
    }

    //Añade indefinidamente items del buffer en instancias aleatorias (Thread.sleep(random))
    //Verificará si NO está lleno para añadir (producir) items.
    @Override
    public void run() {
        while (true) {
            if (!(storageBuffer.isFull)) {
                storageBuffer.addPackage("Package: "+storageBuffer.numOfPackages);
            }

            try {
                Thread.sleep((long) (Math.random()*250));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void startThread() {
        producerThread.start();
    }

    public Thread getProducerThread() {
        return producerThread;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }
}
