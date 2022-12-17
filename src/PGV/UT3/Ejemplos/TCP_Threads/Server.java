package PGV.UT3.Ejemplos.TCP_Threads;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server implements Runnable {
    private final Socket clientSocket;
    private static ArrayList<String> msgList = new ArrayList<>();
    private static int msgPos = 0;

    public Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
        new Thread(this).start();
    }

    public static void main(String args[]) throws IOException, InterruptedException {

        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server connected!\n");

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();

                new Server(clientSocket);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName()+" online.");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while (true) {
                String msg = bufferedReader.readLine();

                msgList.add(msg);

                if (!msg.equalsIgnoreCase("Exit")) {
                    System.out.println(Thread.currentThread().getName()+": "+msgList.get(msgPos++));
                } else {
                    break;
                }
            }

            clientSocket.close();

        } catch (Exception ignored) {
        }
    }

}
