package PGV.UT2.RentACar;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        CarStorageBuffer carStorageBuffer = new CarStorageBuffer();

        for (int i = 0; i<new Random().nextInt(10,50); i++) {
            Client client = new Client("Cliente"+i,carStorageBuffer);
            client.getThread().start();
        }
    }
}
