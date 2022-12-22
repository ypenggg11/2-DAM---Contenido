package PGV.UT3.Actividad1.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("Connected!");

            InetAddress serverIp = InetAddress.getLocalHost();
            DatagramSocket clientSocket = new DatagramSocket();

            String msg = "Hi!";

            DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length,serverIp,Server.PORT);

            clientSocket.send(msgPacket);

            System.out.println("Message sent!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }
}
