package PGV.UT2.Actividad2RentACar.data_class;

public class Car {

    private String carId;

    public Car(String carId){
        this.carId = carId;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }
}
