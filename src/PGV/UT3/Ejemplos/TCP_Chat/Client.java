package PGV.UT3.Ejemplos.TCP_Chat;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client implements Runnable {

    private BufferedReader clientReader;
    private OutputStream clientWriter;

    @Override
    public void run() {
        try {

            Socket clientSocket = new Socket();

            InetSocketAddress address = new InetSocketAddress("127.0.0.1", Server.PORT);

            clientSocket.connect(address);

            clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientWriter = clientSocket.getOutputStream();

            new Thread(new InputHandler()).start();

             String msg;
             while ((msg = clientReader.readLine())!= null) {
                 System.out.println(msg);
             }

             clientSocket.close();

        } catch (IOException e) {
            System.exit(0);
        }
    }

    class InputHandler implements Runnable {

        @Override
        public void run() {

            try {
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    String msg = inputReader.readLine();
                    clientWriter.write((msg+"\n").getBytes());
                    clientWriter.flush();
                }

            } catch (IOException ignored) {
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }
}
