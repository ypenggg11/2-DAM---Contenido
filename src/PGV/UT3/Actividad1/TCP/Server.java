package PGV.UT3.Actividad1.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server implements Runnable {

    final static int PORT = 6000;

    //private final ArrayList<Connection> clientsList = new ArrayList<>();
    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Waiting connections...");

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(new Connection(socket)).start();

                //clientsList.add(connection);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* Broadcast message to other clients method */



    /* Connection shutdown method */


    class Connection implements Runnable {

        private final Socket socket;

        /* Client reader and writer declarations */

        private BufferedReader clientReader;

        Connection(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {

                /* Initialize reader and writer */

                this.clientReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

                /* Manage IN and OUT operations */

                String clientIp = this.socket.getInetAddress().getHostAddress();
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
            }
        }

        /* Send message method */


    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
