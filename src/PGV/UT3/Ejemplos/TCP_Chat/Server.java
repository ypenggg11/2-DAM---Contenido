package PGV.UT3.Ejemplos.TCP_Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    //TODO {
    //      1.- Informar mejor tanto en el servidor como en el usuario
    //      2.- Formatear y comentar codigo
    // }
    final static int PORT = 6000;
    private final ArrayList<ConnectionHandler> clientList = new ArrayList<>();

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {

                Socket clientSocket = serverSocket.accept();

                ConnectionHandler connHandler = new ConnectionHandler(clientSocket);
                new Thread(connHandler).start();

                clientList.add(connHandler);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcastMessage(String message, ConnectionHandler connectionHandler) {
        for (ConnectionHandler ch: clientList) {
            if (!ch.equals(connectionHandler)) ch.sendMessage(message);
        }
    }

    public void shutdown(ConnectionHandler connectionHandler) {
        clientList.remove(connectionHandler);
    }

    class ConnectionHandler implements Runnable {
        private final Socket socket;
        private String username;
        private BufferedReader clientReader;
        private OutputStream clientWriter;

        ConnectionHandler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {

            try {

                clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clientWriter = socket.getOutputStream();

                sendMessage("Enter a username: ");
                username = clientReader.readLine();
                broadcastMessage(username+" connected",this);

                String input;
                while (true) {
                    input = clientReader.readLine();
                    broadcastMessage(username + ": "+input,this);
                }

            } catch (IOException e) {
                shutdown(this);
                try {
                    broadcastMessage(username+" disconnected...",this);
                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        private void sendMessage(String message) {
            try {
                clientWriter.write((message+"\n").getBytes());
                clientWriter.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
