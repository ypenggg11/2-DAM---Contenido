package PGV.UT3.Examen;

import java.io.IOException;
import java.net.*;

public class Servidor implements Runnable {

    final static int PORT = 25666;

    @Override
    public void run() {
        try {
            /* Creamos un datagramsocket y le asignamos un puerto para que escuche por esta */
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("Waiting for messages...");

            /* Comienza a recibir mensajes del cliente, una vez recibido, ejecutará
            *  un hilo separado para la escritura de las respuestas enviadas al cliente */
            while (true) {
                byte[] input = new byte[1024];

                DatagramPacket packet = new DatagramPacket(input, input.length);
                serverSocket.receive(packet);

                String msg = new String(packet.getData()).trim();
                System.out.println("Received: " + msg.trim());

                new MessageWriter(msg,serverSocket,packet.getAddress(), packet.getPort());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class MessageWriter implements Runnable {

        private final String msg;
        private final InetAddress destinationIp;
        private final Integer destinationPort;
        private final DatagramSocket socket;
        private final Thread thread;

        MessageWriter(String msg, DatagramSocket socket, InetAddress destinationIp, Integer destinationPort) {
            this.msg = msg;
            this.socket = socket;
            this.destinationIp = destinationIp;
            this.destinationPort = destinationPort;

            this.thread = new Thread(this);
            this.thread.start();
        }

        @Override
        public void run() {
            try {
                /* Enviamos un OK recibido, y si es la acción upload, enviará otro mensaje más */

                String reply = "OK recibido!";
                sendMsg(reply);

                if (msg.toUpperCase().contains("UPLOAD:")) {
                    String fileReply = "Se han subido "+ (int)(Math.random() * 100) +" archivos";
                    sendMsg(fileReply);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /* Mediante el paquete que recibimos del cliente, obtenemos su dirección y puerto,
        *  para enviarlo al mismo lugar de origen */
        private void sendMsg(String reply) throws IOException {
            DatagramPacket msgPacket = new DatagramPacket(reply.getBytes(), reply.getBytes().length, destinationIp, destinationPort);
            socket.send(msgPacket);
        }

    }

    public static void main(String[] args) {
        new Thread(new Servidor()).start();
    }
}
