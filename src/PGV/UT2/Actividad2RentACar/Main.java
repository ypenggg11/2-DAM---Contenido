package PGV.UT2.Actividad2RentACar;

import PGV.UT2.Actividad2RentACar.producer_consumer_model.CarStorageBuffer;
import PGV.UT2.Actividad2RentACar.producer_consumer_model.Client;

import java.util.Random;

/*
 * Enunciado:
 * 1.- El cliente será consumidor y productor al mismo tiempo.
 * 2.- El cliente podrá alquilar coches y luego de un tiempo indefinido, devolverlos.
 * 3.- El cliente no podrá alquilar coches si no quedan, y esperará otro tiempo indefinido
 *     para volver a intentar alquilar de nuevo.
 * */
public class Main {
    private static CarStorageBuffer carStorageBuffer;
    public static void main(String[] args) {
        carStorageBuffer = new CarStorageBuffer();

        initClients();
    }

    private static void initClients() {
        int min = 10,max = 50, numClients = new Random().nextInt(min,max);
        System.out.println("Clients amount: "+numClients);

        for (int i = 0; i<numClients; i++) {
            Client client = new Client("Client "+i,carStorageBuffer);
            client.getThread().start();
        }
    }
}
