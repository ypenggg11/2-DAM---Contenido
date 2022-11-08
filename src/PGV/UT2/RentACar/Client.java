package PGV.UT2.RentACar;

//Producer and Consumer at the same time.
public class Client implements Runnable{

    private final Thread thread;
    private String clientId;
    private CarStorageBuffer carStorageBuffer;

    Client(String id,CarStorageBuffer carStorageBuffer) {
        this.clientId = id;
        this.carStorageBuffer = carStorageBuffer;

        this.thread = new Thread(this);
        this.thread.setName(this.clientId);
    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep((long) (Math.random()*8000));

                Car car;

                if((car = carStorageBuffer.pullCar())!=null) {
                    System.out.print("\033[0;31m");
                    System.out.println("Car "+car.getCarId() + " has been PULLED by client "+clientId+"!");

                    Thread.sleep((long) (Math.random()*8000));

                    carStorageBuffer.pushCar(car);

                    System.out.print("\033[0;32m");
                    System.out.println("Car "+car.getCarId() + " has been PUSHED by client "+clientId+"!");
                }else {
                    System.out.print("\033[0m");
                    System.out.println("Pull failed by client "+clientId+"!");
                }
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
