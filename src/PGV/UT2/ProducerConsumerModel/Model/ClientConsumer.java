package PGV.UT2.ProducerConsumerModel.Model;

import PGV.UT2.ProducerConsumerModel.ExecuteThread;

public class ClientConsumer implements Runnable, ExecuteThread {

    //Se ejecutará sobre un hilo nuevo.
    private final Thread consumerThread= new Thread(this);
    //Buffer al que accederemos para consumir items.
    private StorageBuffer storageBuffer;
    private String consumerName;

    public ClientConsumer(StorageBuffer buffer,String consumerName) {
        this.storageBuffer = buffer;
        this.consumerName = consumerName;
        this.consumerThread.setName(consumerName);
    }

    //Consume indefinidamente items del buffer en instancias aleatorias (Thread.sleep(random))
    //Verificará si NO está vacío para sacar (consumir) items.
    @Override
    public void run() {
        while (true) {
            if (!(storageBuffer.isEmpty)) {
                storageBuffer.takePackage();
            }

            try {
                Thread.sleep((long) (Math.random()*600));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void startThread() {
        consumerThread.start();
    }

    public Thread getConsumerThread() {
        return consumerThread;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }
}
