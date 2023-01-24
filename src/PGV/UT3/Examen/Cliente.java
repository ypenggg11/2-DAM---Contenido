package PGV.UT3.Examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente implements Runnable {

    /* Lectura desde teclado */
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void run() {
        try {

            /* Nos conectamos al servidor */
            InetAddress serverIp = InetAddress.getLocalHost();

            DatagramSocket clientSocket = new DatagramSocket();
            clientSocket.connect(serverIp, Servidor.PORT);
            System.out.println("Connected!");

            /* Empieza a leer desde teclado, y si no es ###,
               enviará el mensaje al servidor y ejecutará un nuevo hilo para leer
               las respuestas enviados desde el servidor al cliente */

            String msg;
            while (!(msg = reader.readLine()).equals("###")) {

                DatagramPacket msgPacket = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverIp, Servidor.PORT);
                clientSocket.send(msgPacket);

                new ReplyReader(clientSocket, msg);
            }

            System.out.println("Disconnected");
            clientSocket.disconnect();
            clientSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class ReplyReader implements Runnable {

        private final DatagramSocket socket;
        private final Thread thread;
        private final String msg;

        ReplyReader(DatagramSocket socket, String msg) {
            this.socket = socket;
            this.msg = msg;

            this.thread = new Thread(this);
            this.thread.start();
        }

        @Override
        public void run() {
            try {
                /* Si tiene el comando especial UPLOAD:, leerá 2 veces, ya que el servidor
                *  responde 2 veces también */
                receiveMsg();

                if (msg.toUpperCase().contains("UPLOAD:")) {
                    receiveMsg();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /* Recibimos el paquete y lo mostramos por pantalla */
        private void receiveMsg() throws IOException {
            byte[] reply = new byte[1024];
            DatagramPacket replyPacket = new DatagramPacket(reply, reply.length);
            socket.receive(replyPacket);

            System.out.println(new String(replyPacket.getData()).trim());
        }
    }

    public static void main(String[] args) {
        new Thread(new Cliente()).start();
    }
}
