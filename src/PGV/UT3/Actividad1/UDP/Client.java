package PGV.UT3.Actividad1.UDP;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private Scanner scanner = new Scanner(System.in);
    private OutputStream clientWriter;
    @Override
    public void run() {
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), Server.PORT);
            Socket socket = new Socket();

            socket.connect(inetSocketAddress);
            clientWriter = socket.getOutputStream();

            while (true) {
                if (scanner.nextLine().equalsIgnoreCase("EXIT")) {
                    clientWriter.write((inetSocketAddress.getAddress().getHostAddress()+" disconnected!\n").getBytes());
                    socket.close();
                    break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new Thread(new PGV.UT3.Actividad1.TCP.Client()).start();
    }
}
