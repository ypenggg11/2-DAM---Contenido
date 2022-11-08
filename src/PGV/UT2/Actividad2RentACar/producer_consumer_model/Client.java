package PGV.UT2.Actividad2RentACar.producer_consumer_model;

import PGV.UT2.Actividad2RentACar.data_class.Car;

public class Client implements Runnable{

    private final Thread thread;
    private String clientId;
    private CarStorageBuffer carStorageBuffer;

    public Client(String id, CarStorageBuffer carStorageBuffer) {
        this.clientId = id;
        this.carStorageBuffer = carStorageBuffer;

        this.thread = new Thread(this);
        this.thread.setName(this.clientId);
    }

    @Override
    public void run() {

        try {
            while (true) {
                Car car;

                /*
                 * En el caso de que en un inicio haya más clientes que coches, como al
                 * principio no hay un Thread.sleep(), accederán todos a hacer un .pullCar,
                 * y como el Sout del else requiere mejos trabajo para mostrarse
                 * que el del if, se mostrará primero que ha fallado el intento de pull.
                 * */
                if((car = carStorageBuffer.pullCar())!=null) {
                    System.out.print("\033[0;31m"); //Color rojo
                    System.out.println("Car "+car.getCarId() + " has been PULLED by client "+clientId+"!");

                    Thread.sleep((long) (Math.random()*8000));

                    carStorageBuffer.pushCar(car);

                    System.out.print("\033[0;32m"); //Color verde
                    System.out.println("Car "+car.getCarId() + " has been PUSHED by client "+clientId+"!");
                }else {
                    System.out.print("\033[0m"); //Color default
                    System.out.println("Pull failed by client "+clientId+"!");
                }

                Thread.sleep((long) (Math.random()*8000));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Thread getThread() {
        return this.thread;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public CarStorageBuffer getCarStorageBuffer() {
        return carStorageBuffer;
    }

    public void setCarStorageBuffer(CarStorageBuffer carStorageBuffer) {
        this.carStorageBuffer = carStorageBuffer;
    }
}
