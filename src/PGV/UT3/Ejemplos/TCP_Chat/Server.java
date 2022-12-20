package PGV.UT3.Ejemplos.TCP_Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
* Clase Server, el main creará una instancia del Server, que se ejecutará en un Thread distinto,
* manejando la entrada de las conexiones con el server socket, y cada conexión aceptada se creará
* una nueva instancia de una clase interna Connection, para manejar cada conexión (cada cliente será
* una conexión, con su propio hilo y su propio socket), y se añadirá a la lista de conexiones del Server.
* */
public class Server implements Runnable{
    final static int PORT = 6000;
    private final ArrayList<Connection> clientList = new ArrayList<>();

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting for connections...");

            while (true) {

                Socket clientSocket = serverSocket.accept();

                Connection connHandler = new Connection(clientSocket);
                new Thread(connHandler).start();

                clientList.add(connHandler);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadcastMessage(String message, Connection connection) {
        //Envía el mensaje a todos los otros clientes conectados.
        for (Connection ch: clientList) {
            if (!ch.equals(connection)) {
                if (ch.username!=null) {
                    ch.sendMessage(message);
                }
            }
        }
    }

    //Elimina el cliente de las conexiones.
    public void shutdownConnection(Connection connection) {
        clientList.remove(connection);
    }

    //Clase interna para manejar las conexiones del servidor.
    class Connection implements Runnable {
        private final Socket socket;
        private BufferedReader clientReader;
        private OutputStream clientWriter;
        private String username;

        Connection(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {

            try {

                //Creamos las instancias de la entrada y salida de datos.
                clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clientWriter = socket.getOutputStream();

                //Solicitamos un nombre de usuario al cliente y mostramos la conexión tanto
                //en el servidor como en los clientes conectados.
                sendMessage("Enter a username: ");
                username = clientReader.readLine();
                System.out.println(username+" connected");
                broadcastMessage(username+" connected",this);

                String input;

                //Una vez conectados con el nombre de usuario, estará a la escucha de mensajes
                //enviados por los clientes y los enviará a los otros clientes conectados.
                while (true) {
                    input = clientReader.readLine();
                    broadcastMessage(username + ": "+input,this);
                }

            } catch (IOException e) {
                //En caso de excepción (cliente se desconecta)
                shutdownConnection(this);
                try {
                    //Muestra a los conectados el usuario que se desconectó
                    if (username!=null) {
                        System.out.println(username+" disconnected");
                        broadcastMessage(username+" disconnected...",this);
                    }
                    //Y cierra el socket del cliente
                    socket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        //Envía un mensaje utilizando el flujo de salida. (de este socket (cliente) en concreto)
        private void sendMessage(String message) {
            try {
                //No funciona sin "\n"
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
