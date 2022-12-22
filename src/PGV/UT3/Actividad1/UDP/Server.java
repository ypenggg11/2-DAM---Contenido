package PGV.UT3.Actividad1.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

public class Server implements Runnable{

    final static int PORT = 6000;

    @Override
    public void run() {

        try {
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("Waiting connections...");

            while (true) {
                byte[] input = new byte[1024];

                DatagramPacket datagramPacket = new DatagramPacket(input, input.length);
                serverSocket.receive(datagramPacket);

                new Thread(new Connection(datagramPacket)).start();

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Connection implements Runnable {

        private final DatagramPacket packet;

        Connection(DatagramPacket packet) {
            this.packet = packet;
        }

        @Override
        public void run() {

            String clientIp = this.packet.getAddress().getHostAddress();
            String threadName = Thread.currentThread().getName();
            String currentDate = new Date(System.currentTimeMillis()).toString();

            System.out.println(threadName + " connected with IP: " + clientIp + " - at " + currentDate);

            String msg = new String(packet.getData());

            System.out.println(threadName+" sent: "+ msg.trim());
        }

    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
