package PGV.UT3.Actividad1.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Date;

public class Server implements Runnable{

    final static int PORT = 6000;

    //private final ArrayList<Connection> clientsList = new ArrayList<>();
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

                //clientsList.add(connection);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Broadcast message to other clients method */



    /* Connection shutdown method */


    class Connection implements Runnable {

        private final DatagramPacket packet;

        Connection(DatagramPacket packet) {
            this.packet = packet;
        }

        //TODO UDP Multithreading exercise
        @Override
        public void run() {
            /*try {

                String clientIp = this.packet.getAddress().getHostAddress();
                String threadName = Thread.currentThread().getName();
                String currentDate = new Date(System.currentTimeMillis()).toString();

                System.out.println(threadName + " connected with IP: " + clientIp + " - at " + currentDate);

                String msg;

                while (true) {
                    if (((msg = clientReader.readLine()) != null)) {
                        System.out.println(Thread.currentThread().getName()+" with IP: "+msg);
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }*/
        }

        /* Send message method */


    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
