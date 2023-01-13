package PGV.UT3.Ejemplos.ChatMultiCast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Main implements Runnable {

    //TODO Mejorar código
    public static final String host = "225.1.1.1";
    public static final int port = 6999;

    @Override
    public void run() {
        try {
            MulticastSocket multicastSocket = new MulticastSocket(port);

            InetAddress group = InetAddress.getByName(host);
            multicastSocket.joinGroup(group);

            new Thread(new Connection(multicastSocket,group)).start();

            String msg = "";

            while (!(msg.equalsIgnoreCase("exit"))) {
                byte[] buffer = new byte[1000];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                multicastSocket.receive(packet);
                msg = new String(packet.getData()).trim();
                System.out.println(msg);
            }

            multicastSocket.leaveGroup(group);
            multicastSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Connection implements Runnable {
        private BufferedReader bufferedReader;
        private MulticastSocket multicastSocket;
        private InetAddress inetAddress;

        Connection(MulticastSocket multicastSocket, InetAddress inetAddress) {
            this.multicastSocket = multicastSocket;
            this.inetAddress = inetAddress;
        }

        @Override
        public void run() {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            try {
                String msg = "";

                while (!(msg.equalsIgnoreCase("exit"))) {
                    System.out.println("Datos para enviar: ");

                    msg = bufferedReader.readLine();
                    DatagramPacket paquete = new DatagramPacket(msg.getBytes(), msg.length(), inetAddress, port);
                    multicastSocket.send(paquete);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Main()).start();
    }
}
