package PGV.UT2.Actividad2RentACar.producer_consumer_model;

import PGV.UT2.Actividad2RentACar.data_class.Car;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class CarStorageBuffer {

    private final ArrayList<Car> carsList = new ArrayList<>();
    private final Semaphore semaphore = new Semaphore(1);
    public static final int MAX_STORAGE = 20;
    public static final int MIN_STORAGE = 0;
    private int currentCars;

    public CarStorageBuffer(){
        initCarsList();
        this.currentCars = carsList.size();
    }

    private void initCarsList() {
        for (int i = 0;i<MAX_STORAGE;i++){
            carsList.add(new Car((String.valueOf(i))));
        }
    }

    public boolean pushCar(Car car) {
        try {
            semaphore.acquire();

            /*
            * No hace falta comprobar la cantidad máxima en este caso, ya que
            * al ser el productor el mismo consumidor, nunca devolverá un coche que
            * no haya existido en el buffer, por lo tanto, no pasará de la cantidad máxima.
            * */
            if (currentCars<MAX_STORAGE){
                carsList.add(car);
                currentCars++;

                semaphore.release();
                return true;
            }

            semaphore.release();
            return false;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Car pullCar() {
        try {
            semaphore.acquire();

            if (currentCars>MIN_STORAGE){
                Car car = carsList.get(currentCars - 1);
                carsList.remove(currentCars - 1);
                currentCars--;

                semaphore.release();
                return car;
            }

            semaphore.release();
            return null;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCurrentCars(int currentCars) {
        this.currentCars = currentCars;
    }
}
