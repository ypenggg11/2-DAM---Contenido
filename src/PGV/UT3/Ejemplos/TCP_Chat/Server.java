package PGV.UT3.Ejemplos.TCP_Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    //TODO FIX CHAT
    final static int PORT = 6000;
    private final static ArrayList<Socket> clientList = new ArrayList<>();
    private final static ArrayList<OutputStreamWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);

            while (true) {

                Socket clientSocket = serverSocket.accept();
                BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStreamWriter clientWriter = new OutputStreamWriter(clientSocket.getOutputStream());

                clientList.add(clientSocket);
                clientWriters.add(clientWriter);

                new Server(clientReader,clientWriter,clientList.indexOf(clientSocket));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final Thread thread;
    private final BufferedReader clientReader;
    private final OutputStreamWriter clientWriter;

    Server(BufferedReader clientReader,OutputStreamWriter clientWriter,int clientId){
        this.clientReader = clientReader;
        this.clientWriter = clientWriter;

        thread = new Thread(this);
        thread.setName("Client "+clientId);
        thread.start();
    }

    @Override
    public void run() {

        try {

            String input;

            while (true) {
                input = clientReader.readLine();
                System.out.println(Thread.currentThread().getName() + ": " + input);

                for(OutputStreamWriter osw: clientWriters){
                    if(!osw.equals(clientWriter)) osw.write(Thread.currentThread().getName() + ": " + input);
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
