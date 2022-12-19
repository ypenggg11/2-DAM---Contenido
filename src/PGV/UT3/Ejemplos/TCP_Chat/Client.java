package PGV.UT3.Ejemplos.TCP_Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client implements Runnable {

    //TODO FIX CHAT
    final static int READ_THREAD = 0;
    final static int WRITE_THREAD = 1;

    public static void main(String[] args) {

        try {

            Socket clientSocket = new Socket();

            InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), Server.PORT);

            clientSocket.connect(address);

            new Client(clientSocket, WRITE_THREAD);
            new Client(clientSocket, READ_THREAD);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Thread thread;
    private final Socket clientSocket;
    private final int threadType;

    Client(Socket clientSocket, int threadType) {
        this.clientSocket = clientSocket;

        this.threadType = threadType;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        BufferedReader clientReader = null;
        OutputStreamWriter clientWriter = null;

        try {
            clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientWriter = new OutputStreamWriter(clientSocket.getOutputStream());

            String input;

            while (true) {
                if (threadType == 1) {
                    System.out.println("Write something!");
                    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                    input = inFromUser.readLine();
                    clientWriter.write(input + '\n');
                } else if (threadType == 0) {
                    input = clientReader.readLine();
                    System.out.println(input);
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    public Thread getThread() {
        return thread;
    }
}
